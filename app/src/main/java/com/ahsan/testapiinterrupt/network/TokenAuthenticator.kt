package com.ahsan.testapiinterrupt.network

import com.ahsan.testapiinterrupt.api.ApiService
import com.ahsan.testapiinterrupt.helper.Preference
import com.ahsan.testapiinterrupt.helper.TokenManager
import com.ahsan.testapiinterrupt.models.RefreshTokenResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class TokenAuthenticator() : Authenticator, KoinComponent {

    private val tokenManager: TokenManager by inject()

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            //Preference.getInstance().getRefreshToken()
            tokenManager.getToken().first()
        }

        return runBlocking {
            val newToken = getFreshToken(token)

            if(!newToken.isSuccessful || newToken.body()==null){
                //Preference.getInstance().deleteRefreshToken()
                tokenManager.deleteToken()
            }

            newToken.body()?.let {
                Preference.getInstance().setBearerToken(it.token)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.token}")
                    .build()
            }
        }
    }

    private suspend fun getFreshToken(refreshToken: String?): retrofit2.Response<RefreshTokenResponse>{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(ApiService::class.java)
        return service.refreshToken("Bearer $refreshToken")

    }


}