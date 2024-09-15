package com.example.eatsygo_app.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsManager {

    private const val PREFS_NAME = "auth_prefs"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveValue(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getValue(key: String) = preferences.getString(key, null)

    fun saveBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String) = preferences.getBoolean(key, false)

}