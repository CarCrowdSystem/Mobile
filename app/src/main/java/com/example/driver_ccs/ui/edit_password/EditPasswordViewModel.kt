package com.example.driver_ccs.ui.edit_password

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.editPassword.EditPasswordRepository
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.ValidationModel

class EditPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val editPasswordRepository = EditPasswordRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private var _status = MutableLiveData<ValidationModel?>()
    val status: LiveData<ValidationModel?> = _status

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun editPassword(oldPassword: String, newPassword: String) {
        _isLoading.value = true
        val id = securityPreferences.get("id")

        editPasswordRepository.editPassword(oldPassword, newPassword, id.toInt(), object : ApiListener<Unit> {
            override fun onSuccess(result: Unit) {
                _isLoading.value = false
                _status.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _isLoading.value = false
                _status.value = ValidationModel(message)
            }
        })
    }

    fun clearStatus() {
        _status.value = null
    }

}