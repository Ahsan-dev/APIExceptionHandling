package com.ahsan.testapiinterrupt

import android.app.Application
import android.content.Context
import com.ahsan.testapiinterrupt.di.appModule
import com.ahsan.testapiinterrupt.helper.Preference
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class APIIntertuptApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@APIIntertuptApp)
            modules(listOf(appModule))
        }
    }

    override fun attachBaseContext(base: Context?) {

        super.attachBaseContext(base)

        if (base != null) {
            Preference.getInstance(base)
        }

    }
}