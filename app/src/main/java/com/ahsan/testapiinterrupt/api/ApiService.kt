package com.ahsan.testapiinterrupt.api

import com.ahsan.testapiinterrupt.models.LoginResponse
import com.ahsan.testapiinterrupt.models.RefreshTokenResponse
import com.ahsan.testapiinterrupt.models.UserInfoResponse
import com.ahsan.testapiinterrupt.models.request.LoginReq
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("")
    suspend fun refreshToken(
        @Header("Authorization") token : String
    ):Response<RefreshTokenResponse>

    @POST("auth/login")
    @FormUrlEncoded
    suspend fun login(
        @Body loginReq: LoginReq
    ):Response<LoginResponse>

    @GET("profile/user-info")
    suspend fun getUserInfo(
        @Query("id") id : Int
    ):Response<UserInfoResponse>

}