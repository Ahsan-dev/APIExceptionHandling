package com.ahsan.testapiinterrupt.ui.info

import androidx.lifecycle.MutableLiveData
import com.ahsan.testapiinterrupt.helper.ApiResponse
import com.ahsan.testapiinterrupt.helper.BaseViewModel
import com.ahsan.testapiinterrupt.helper.CoroutineErrorHandler
import com.ahsan.testapiinterrupt.models.UserInfoResponse
import com.ahsan.testapiinterrupt.repository.InfoRepository

class InfoViewModel : BaseViewModel() {

    private val repo: InfoRepository = InfoRepository()
    private val _infoResponse = MutableLiveData<ApiResponse<UserInfoResponse>>()
    val infoResponse = _infoResponse

    fun getInfo(id: Int, coroutineErrorHandler: CoroutineErrorHandler) = baseRequest(
        _infoResponse,
        coroutineErrorHandler,
    ){
        repo.getUserInfo(id)
    }

}