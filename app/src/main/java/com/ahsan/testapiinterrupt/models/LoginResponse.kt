package com.ahsan.testapiinterrupt.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access-token")
    val token : String
)
