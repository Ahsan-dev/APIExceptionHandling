package com.ahsan.testapiinterrupt.repository

import com.ahsan.testapiinterrupt.api.ApiService
import com.ahsan.testapiinterrupt.helper.apiRequestFlow
import com.ahsan.testapiinterrupt.models.request.LoginReq
import com.ahsan.testapiinterrupt.network.RetrofitAdapter

class AuthRepository() {

    private fun getClient() : ApiService?{
        return RetrofitAdapter.apiService
    }

    fun login(auth: LoginReq) = apiRequestFlow {
        getClient()!!.login(auth)
    }
}