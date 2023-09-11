package com.example.driver_ccs.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.login.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application,
    private val loginRepository: LoginRepository
) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private var _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    fun doLogin(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModelScope.launch {
//                val resource = loginRepository.login(email, password)
                securityPreferences.store("email", email)
                securityPreferences.store("password", password)

                _login.value = true
            }
        }
    }

}