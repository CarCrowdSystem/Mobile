package com.example.driver_ccs.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.SecurityPreferences

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    fun logout() {
        securityPreferences.remove("nome")
        securityPreferences.remove("id")
    }
}
