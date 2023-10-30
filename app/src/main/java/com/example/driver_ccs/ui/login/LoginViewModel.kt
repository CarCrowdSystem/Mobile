package com.example.driver_ccs.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.login.LoginRepository
import com.example.driver_ccs.data.remote.model.LoginModel
import com.example.driver_ccs.data.remote.model.ValidationModel

class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val loginRepository = LoginRepository(application.applicationContext)

    private var _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    fun doLogin(email: String, password: String) {
        loginRepository.login(email, password, object : ApiListener<LoginModel> {
            override fun onSuccess(result: LoginModel) {

                securityPreferences.store("email", email)
                securityPreferences.store("senha", password)

                _login.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _login.value = ValidationModel(message)
            }
        })
    }
}