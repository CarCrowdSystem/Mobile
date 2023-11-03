package com.example.driver_ccs.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.SecurityPreferences

class   HomeViewModel(application: Application) : AndroidViewModel(application)  {

    private val _listParking = MutableLiveData<List<ParkinglLotHome>>()
    val listParking : LiveData<List<ParkinglLotHome>> =  _listParking

    private var _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    fun getParkingLots() {
        _listParking.value =
            listOf(
                ParkinglLotHome("Car park","Rua consolação, 123 - Bairro, Cidade, SP", "R$ 15,00","12/50","2km"),
                ParkinglLotHome("Car park","Rua consolação, 123 - Bairro, Cidade, SP", "R$ 15,00","12/50","2km"),
                ParkinglLotHome("Car park","Rua consolação, 123 - Bairro, Cidade, SP", "R$ 15,00","12/50","2km"),
                ParkinglLotHome("Car park","Rua consolação, 123 - Bairro, Cidade, SP", "R$ 15,00","12/50","2km"),
                ParkinglLotHome("Car park","Rua consolação, 123 - Bairro, Cidade, SP", "R$ 15,00","12/50","2km")
            )
    }

    fun verifyLoggedUser() {
        val nome = securityPreferences.get("nome")
        val id = securityPreferences.get("id")

        _userLogged.value = (nome.isNotEmpty() && id.isNotEmpty())
    }

}

data class ParkinglLotHome(
    val nome: String,
    val endereco: String,
    val valorHora: String,
    val vagasDisponiveis: String,
    val distancia: String)