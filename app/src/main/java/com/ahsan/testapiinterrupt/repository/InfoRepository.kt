package com.ahsan.testapiinterrupt.repository

import com.ahsan.testapiinterrupt.api.ApiService
import com.ahsan.testapiinterrupt.helper.apiRequestFlow
import com.ahsan.testapiinterrupt.network.RetrofitAdapter

class InfoRepository() {
    private fun getClient() : ApiService?{
        return RetrofitAdapter.apiService
    }

    fun getUserInfo(id:Int)= apiRequestFlow {
        getClient()!!.getUserInfo(id)
    }
}