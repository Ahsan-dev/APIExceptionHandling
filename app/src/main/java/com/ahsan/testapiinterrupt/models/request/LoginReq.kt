package com.ahsan.testapiinterrupt.models.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginReq(
    @SerializedName("email")
    @Expose
    var email: String?,
    @SerializedName("password")
    @Expose
    var password: String?
)