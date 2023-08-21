package com.example.driver_ccs.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private var _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login
    fun doLogin(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()){
            Log.d("***Teste", "User Logged")
            _login.value = true
        }
    }
}