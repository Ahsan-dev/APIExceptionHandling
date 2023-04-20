package com.ahsan.testapiinterrupt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahsan.testapiinterrupt.helper.Preference
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}