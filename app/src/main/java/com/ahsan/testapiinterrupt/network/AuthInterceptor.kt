package com.ahsan.testapiinterrupt.network

import com.ahsan.testapiinterrupt.helper.Preference
import com.ahsan.testapiinterrupt.helper.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject

class AuthInterceptor() : Interceptor, KoinComponent {

    private val  tokenManager: TokenManager by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            //Preference.getInstance().getBearerToken();
            tokenManager.getToken()
        }

        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}