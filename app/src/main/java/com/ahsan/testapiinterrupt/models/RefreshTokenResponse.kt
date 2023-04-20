package com.ahsan.testapiinterrupt.models

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(
    @SerializedName("refresh-token")
    val token : String
)
