package com.ahsan.testapiinterrupt.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class InfoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InfoViewModel::class.java)){
            return InfoViewModel() as T
        }else{
            throw IllegalArgumentException("No viewmodel found")
        }
    }
}