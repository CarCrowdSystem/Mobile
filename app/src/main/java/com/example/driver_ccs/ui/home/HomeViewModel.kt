package com.example.driver_ccs.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.model.ParkingLotModel

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _listParking = MutableLiveData<List<ParkingLotModel>>()
    val listParking: LiveData<List<ParkingLotModel>> = _listParking

    private var _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    fun getParkingLots() {
        _listParking.value =
            listOf(
                ParkingLotModel(
                    "Car park Rosa",
                    "Rua consolação, 123 - Bairro, Cidade, SP",
                    "R$ 15,00",
                    "30/50",
                    "2km"
                ),
                ParkingLotModel(
                    "Car park Sptech",
                    "Rua consolação, 321 - Hadoock Lobo, São Paulo, SP",
                    "R$ 30,00",
                    "20/50",
                    "6km"
                ),
                ParkingLotModel(
                    "Car park Box",
                    "Rua Logo ali, 222 - Bairro, Cidade, SP",
                    "R$ 25,00",
                    "04/50",
                    "1km"
                ),
                ParkingLotModel(
                    "Car park C6",
                    "Rua Virando ali, 2456 - Bairro, Cidade, SP",
                    "R$ 17,00",
                    "12/50",
                    "2km"
                ),
                ParkingLotModel(
                    "Car park Accenture ",
                    "Rua algum lugar, 123 - Bairro, Cidade, SP",
                    "R$ 22,00",
                    "48/50",
                    "3.5km"
                )
            )
    }

    fun verifyLoggedUser() {
        val nome = securityPreferences.get("nome")
        val id = securityPreferences.get("id")

        _userLogged.value = (nome.isNotEmpty() && id.isNotEmpty())
    }
}