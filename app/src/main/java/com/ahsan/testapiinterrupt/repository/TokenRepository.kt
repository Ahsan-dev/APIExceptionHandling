package com.ahsan.testapiinterrupt.repository

import com.ahsan.testapiinterrupt.api.ApiService
import com.ahsan.testapiinterrupt.network.RetrofitAdapter

class TokenRepository() {

    private fun getClient():ApiService?{
        return RetrofitAdapter.apiService
    }

    suspend fun refreshToken(token:String){
        getClient()!!.refreshToken(token)
    }

}