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
import com.example.driver_ccs.data.remote.model.response.LoginResponseModel

class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val loginRepository = LoginRepository(application.applicationContext)

    private var _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _passwordValue = MutableLiveData<String>()
    val passwordValue: LiveData<String> = _passwordValue

    fun doLogin(email: String, password: String) {
        _isLoading.value = true

        loginRepository.login(email, password, object : ApiListener<LoginResponseModel> {
            override fun onSuccess(result: LoginResponseModel) {
                _isLoading.value = false

                securityPreferences.store("nome", result.nome)
                securityPreferences.store("id", result.id)

                _login.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _isLoading.value = false
                _login.value = ValidationModel(message)
            }
        })
    }

    fun saveUserPassword(password: String) {
        _passwordValue.value = password
        securityPreferences.store("password", password)
    }
}