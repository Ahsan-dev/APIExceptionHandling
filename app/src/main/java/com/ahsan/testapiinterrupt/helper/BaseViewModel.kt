package com.ahsan.testapiinterrupt.helper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import okhttp3.Request
import org.xml.sax.ErrorHandler

open class BaseViewModel : ViewModel() {
    private var mJob : Job? = null

    protected fun <T> baseRequest(liveData: MutableLiveData<T>, errorHandler: CoroutineErrorHandler, request: ()-> Flow<T>){
        mJob = viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler{_,error->
            viewModelScope.launch(Dispatchers.Main) {
                Throwable(error.localizedMessage ?: "Error Occured! Please try again")
                errorHandler.onError(error.localizedMessage ?: "Error Occured! Please try again")
            }
        }) {
            request().collect{
                withContext(Dispatchers.Main){
                    liveData.value = it
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        mJob?.let {
            if(it.isActive){
                it.cancel()
            }
        }
    }
}

interface CoroutineErrorHandler{
    fun onError(message: String)
}