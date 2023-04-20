package com.ahsan.testapiinterrupt.helper

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Preference() {

    companion object{


        private lateinit var sharedPrefs: SharedPreferences
        private lateinit var instance: Preference

        @Synchronized
        fun getInstance(): Preference {
            return instance
        }

        @Synchronized
        fun getInstance(context: Context): Preference {
            sharedPrefs = context.getSharedPreferences("api-pref", MODE_PRIVATE)
            instance = Preference()
            return instance
        }

    }


    private var editor: SharedPreferences.Editor = sharedPrefs.edit()

    fun setBearerToken(token: String?){
        editor.putString("token",token)
        editor.commit()
    }

    fun getBearerToken(): String{
        return "Bearer ${sharedPrefs.getString("token","")}"
    }

    fun deleteBearerToken(){
        editor.remove("token")
    }

    fun setRefreshToken(token: String?){
        editor.putString("refresh",token);
        editor.commit()
    }

    fun getRefreshToken():String = "Bearer ${sharedPrefs.getString("refresh","")}"

    fun deleteRefreshToken(){
        editor.remove("refresh")
    }

}