package com.ahsan.testapiinterrupt.di

import androidx.lifecycle.viewmodel.viewModelFactory
import com.ahsan.testapiinterrupt.helper.Preference
import com.ahsan.testapiinterrupt.helper.TokenManager
import com.ahsan.testapiinterrupt.ui.info.InfoViewModel
import com.ahsan.testapiinterrupt.ui.info.TokenViewModel
import com.ahsan.testapiinterrupt.ui.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import javax.inject.Singleton

val appModule = module(override = true) {
    factory { Preference() }
    factory { TokenManager(androidContext()) }
//    viewModelFactory { LoginViewModel() }
//    viewModelFactory { TokenViewModel() }
//    viewModelFactory { InfoViewModel() }
}