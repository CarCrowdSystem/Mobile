package com.example.driver_ccs.ui.user_profile

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.ValidationModel
import com.example.driver_ccs.data.remote.profile.ProfileRepository

class UserProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val userProfileRepository = ProfileRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private var _defaultPicture = MutableLiveData<String>()
    val defaultPicture: LiveData<String> = _defaultPicture

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _alert = MutableLiveData<ValidationModel>()
    val alert: LiveData<ValidationModel> = _alert

    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    var userPicture = securityPreferences.get("uri")

    fun getDefaultPicture(name: String) {
        userProfileRepository.getProfilePicture(name, object : ApiListener<Any> {
            override fun onSuccess(result: Any) {
                _defaultPicture.value = result.toString()
            }

            override fun onFailure(message: String) {
                Log.d("***error", message)
            }
        })
    }

    fun saveImageUri(selectedImageUri : Uri) {
        securityPreferences.store("uri", selectedImageUri.toString())
    }

    fun updateProfile(nome: String, email: String, telefone: String, cpf: String) {
        _isLoading.value = true
        val id = securityPreferences.get("id").toInt()
        userProfileRepository.updateProfile(nome, telefone, cpf, email, id, object : ApiListener<Unit> {
            override fun onSuccess(result: Unit) {
                _isLoading.value = false
                _alert.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _isLoading.value = false
                _alert.value = ValidationModel(message)
            }
        })
    }

    fun getUserName() {
        _userName.value = securityPreferences.get("nome")
    }
}