package com.example.driver_ccs.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.driver_ccs.data.SecurityPreferences

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private var _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    fun doLogin(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()){
            Log.d("***Teste", "User Logged")
            securityPreferences.store("email", email)
            securityPreferences.store("password", password)

            _login.value = true
        }
    }

}