package com.ahsan.testapiinterrupt.models

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("id")
    val id : Int?,
    @SerializedName("name")
    val name : String?

)
