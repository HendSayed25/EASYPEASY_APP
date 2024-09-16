package com.example.eatsygo_app.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>().apply {
        value = SharedPrefsManager.getBoolean("is_logged_in")
    }
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    private val _isOnBoardingDone = MutableLiveData<Boolean>().apply {
        value = SharedPrefsManager.getBoolean("is_onboarding_done")
    }
    val isOnBoardingDone: LiveData<Boolean> get() = _isOnBoardingDone

    private val _firstname = MutableLiveData<String?>().apply {
        value = SharedPrefsManager.getValue("first_name")
    }
    val firstname: LiveData<String?> get() = _firstname

    private val _lastname = MutableLiveData<String?>().apply {
        value = SharedPrefsManager.getValue("last_name")
    }
    val lastname: LiveData<String?> get() = _lastname

    private val _username = MutableLiveData<String?>().apply {
        value = "${_firstname.value} ${_lastname.value}"
    }
    val username: LiveData<String?> get() = _username

    private val _email = MutableLiveData<String?>().apply {
        value = SharedPrefsManager.getValue("email")
    }
    val email: LiveData<String?> get() = _email

    private val _password = MutableLiveData<String?>().apply {
        value = SharedPrefsManager.getValue("password")
    }
    val password: LiveData<String?> get() = _password

    private val _phone = MutableLiveData<String?>().apply {
        value = SharedPrefsManager.getValue("phone")
    }
    val phone: LiveData<String?> get() = _phone

    private val _orderCount = MutableLiveData<Int>().apply {
        value = SharedPrefsManager.getIntValue("order")
    }
    val orderCount: LiveData<Int> get() = _orderCount

    private val _walletValue = MutableLiveData<Int>().apply {
        value = SharedPrefsManager.getIntValue("wallet")
    }
    val walletValue: LiveData<Int> get() = _walletValue

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
        _username.value = "$firstname $lastname"

        SharedPrefsManager.saveValue("first_name", firstname)
        SharedPrefsManager.saveValue("last_name", lastname)
        SharedPrefsManager.saveValue("user_name", _username.value ?: "")
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
            setLoginState(true)
            true
        } else false
    }

    fun addPoint() {
        val orderValue = (_orderCount.value ?: 0) + 1
        val walletValue = (_walletValue.value ?: 0) + 10

        _orderCount.value = orderValue
        _walletValue.value = walletValue

        SharedPrefsManager.saveIntValue("order", orderValue)
        SharedPrefsManager.saveIntValue("wallet", walletValue)
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