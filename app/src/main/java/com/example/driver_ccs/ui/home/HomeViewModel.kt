package com.example.driver_ccs.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _listParking = MutableLiveData<List<String>>()
    val listParking : LiveData<List<String>> = _listParking

    fun getParkingLots() {
        _listParking.value = listOf("Nome_um", "Nome_dois", "Nome_três", "Nome_quatro", "Nome_cinco")
    }

}