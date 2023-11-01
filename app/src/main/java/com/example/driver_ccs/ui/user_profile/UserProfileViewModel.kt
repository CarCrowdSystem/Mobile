package com.example.driver_ccs.ui.user_profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.CarResponseModel
import com.example.driver_ccs.data.remote.profilePicture.ProfileRepository

class UserProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val userProfileRepository = ProfileRepository(application.applicationContext)

    private var _defaultPicture = MutableLiveData<String>()
    val defaultPicture: LiveData<String> = _defaultPicture

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
}