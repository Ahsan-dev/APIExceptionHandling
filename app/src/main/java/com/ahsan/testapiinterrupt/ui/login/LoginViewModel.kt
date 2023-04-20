package com.ahsan.testapiinterrupt.ui.login

import androidx.lifecycle.MutableLiveData
import com.ahsan.testapiinterrupt.helper.ApiResponse
import com.ahsan.testapiinterrupt.helper.BaseViewModel
import com.ahsan.testapiinterrupt.helper.CoroutineErrorHandler
import com.ahsan.testapiinterrupt.models.LoginResponse
import com.ahsan.testapiinterrupt.models.request.LoginReq
import com.ahsan.testapiinterrupt.repository.AuthRepository

class LoginViewModel : BaseViewModel() {
    private val repo : AuthRepository = AuthRepository()

    private val _loginResponse = MutableLiveData<ApiResponse<LoginResponse>>()
    val loginResponse = _loginResponse

    fun login(loginReq: LoginReq, coroutineErrorHandler: CoroutineErrorHandler)=baseRequest(
        _loginResponse,
        coroutineErrorHandler
    ){
        repo.login(loginReq)
    }
}