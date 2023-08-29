package com.example.driver_ccs.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.driver_ccs.data.SecurityPreferences

class HomeViewModel(application: Application) : AndroidViewModel(application)  {

    private val _listParking = MutableLiveData<List<String>>()
    val listParking : LiveData<List<String>> = _listParking

    private var _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    fun getParkingLots() {
        _listParking.value = listOf("Nome_um", "Nome_dois", "Nome_três", "Nome_quatro", "Nome_cinco")
    }

    fun verifyLoggedUser() {
        val email = securityPreferences.get("email")
        val password = securityPreferences.get("password")

        _userLogged.value = (email.isNotEmpty() && password.isNotEmpty())
    }

}