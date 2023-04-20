package com.ahsan.testapiinterrupt.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahsan.testapiinterrupt.helper.Preference
import com.ahsan.testapiinterrupt.helper.TokenManager
import com.ahsan.testapiinterrupt.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject
import org.koin.java.KoinJavaComponent.inject

class TokenViewModel : ViewModel(), KoinComponent {

    private val tokenManager: TokenManager by inject()
    private val repo : TokenRepository = TokenRepository()

    val token = MutableLiveData<String?>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.getToken().collect{
                withContext(Dispatchers.Main){
                    token.value = it
                }
            }
        }
    }

    fun saveToken(token: String){
        viewModelScope.launch(Dispatchers.IO){
            tokenManager.saveToken(token)
        }
    }

    fun deleteToken(){
        viewModelScope.launch(Dispatchers.IO){
            tokenManager.deleteToken()
        }
    }
}