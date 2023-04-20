package com.ahsan.testapiinterrupt.network

import android.content.Context
import com.ahsan.testapiinterrupt.api.ApiService
import com.ahsan.testapiinterrupt.helper.Preference
import dagger.hilt.android.migration.CustomInjection.inject
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.android.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


object RetrofitAdapter{

    private fun getRetro(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient{

        val tokenAuthenticator: TokenAuthenticator = TokenAuthenticator()
        val authInterceptor: AuthInterceptor = AuthInterceptor()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        okHttpClientBuilder.addInterceptor(authInterceptor)
        okHttpClientBuilder.authenticator(tokenAuthenticator)
        okHttpClientBuilder.connectTimeout(1, TimeUnit.MINUTES)
        okHttpClientBuilder.readTimeout(1, TimeUnit.MINUTES)
        okHttpClientBuilder.writeTimeout(1, TimeUnit.MINUTES)

        return okHttpClientBuilder.build()
    }

    val apiService: ApiService = getRetro().create(ApiService::class.java)
}