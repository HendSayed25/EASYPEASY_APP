package com.example.eatsygo_app.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    private val _isOnBoardingDone = MutableLiveData<Boolean>()
    val isOnBoardingDone: LiveData<Boolean>
        get() = _isOnBoardingDone

    private val _firstname = MutableLiveData<String?>()
    val firstname: MutableLiveData<String?>
        get() = _firstname

    private val _lastname = MutableLiveData<String?>()
    val lastname: MutableLiveData<String?>
        get() = _lastname

    private val _username = MutableLiveData<String?>()
    val username: MutableLiveData<String?>
        get() = _username

    private val _email = MutableLiveData<String?>()
    val email: MutableLiveData<String?>
        get() = _email

    private val _password = MutableLiveData<String?>()
    val password: MutableLiveData<String?>
        get() = _password

    private val _phone = MutableLiveData<String?>()
    val phone: MutableLiveData<String?>
        get() = _phone

    init {
        _isLoggedIn.value = SharedPrefsManager.getBoolean("is_logged_in")
        _isOnBoardingDone.value = SharedPrefsManager.getBoolean("is_onboarding_done")
        _username.value = SharedPrefsManager.getValue("user_name")
        _firstname.value = SharedPrefsManager.getValue("first_name")
        _lastname.value = SharedPrefsManager.getValue("last_name")
        _email.value = SharedPrefsManager.getValue("email")
        _phone.value = SharedPrefsManager.getValue("phone")
        _password.value = SharedPrefsManager.getValue("password")
    }

    fun setLoginState(isLoggedIn: Boolean) {
        _isLoggedIn.value = isLoggedIn
        SharedPrefsManager.saveBoolean("is_logged_in", isLoggedIn)
    }

    fun setOnBoardingState(isOnBoardingDone: Boolean) {
        _isOnBoardingDone.value = isOnBoardingDone
        SharedPrefsManager.saveBoolean("is_onboarding_done", isOnBoardingDone)
    }

    fun setUserName(firstname: String, lastname: String) {
        _firstname.value = firstname
        _lastname.value = lastname
        val username = "$firstname $lastname"
        _username.value = username

        SharedPrefsManager.saveValue("first_name", firstname)
        SharedPrefsManager.saveValue("last_name", lastname)
        SharedPrefsManager.saveValue("user_name", username)
    }

    fun setEmail(email: String) {
        _email.value = email
        SharedPrefsManager.saveValue("email", email)
    }

    fun setPassword(password: String) {
        _password.value = password
        SharedPrefsManager.saveValue("password", password)
    }

    fun setPhone(phone: String) {
        _phone.value = phone
        SharedPrefsManager.saveValue("phone", phone)
    }

    fun checkLogin(email: String, password: String): Boolean {
        return if (email == SharedPrefsManager.getValue("email") && password == SharedPrefsManager.getValue(
                "password"
            )
        ) {
            _isLoggedIn.value = true
            SharedPrefsManager.saveBoolean("is_logged_in", true)
            true
        } else false
    }

    fun clearSensitiveData() {
        _firstname.value = null
        _lastname.value = null
        _username.value = null
        _email.value = null
        _phone.value = null
        _password.value = null
    }

}