package com.ahsan.testapiinterrupt.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TokenManager(private val context: Context) {

    companion object{
        val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = "settings")
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

    fun getToken():Flow<String> {
        return context.datastore.data.map { pref->
            pref[TOKEN_KEY]?:""
        }
    }

    suspend fun saveToken(token:String){
        context.datastore.edit { pref->
            pref[TOKEN_KEY] = token
        }
    }

    suspend fun deleteToken(){
        context.datastore.edit { pref->
            pref.remove(TOKEN_KEY)
        }
    }
}