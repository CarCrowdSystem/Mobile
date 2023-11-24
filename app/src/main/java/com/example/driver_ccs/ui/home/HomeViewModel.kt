package com.example.driver_ccs.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.ParkingLotModel
import com.example.driver_ccs.data.remote.model.response.ParkingLotResponseModel
import com.example.driver_ccs.data.remote.parkingLots.ParkingLotRepository

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val parkingLotRepository = ParkingLotRepository(application.applicationContext)

    private val _listParking = MutableLiveData<List<ParkingLotResponseModel>>()
    val listParking: LiveData<List<ParkingLotResponseModel>> = _listParking

    private var _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    fun getParkingLots() {
        parkingLotRepository.getParkingLots(object : ApiListener<List<ParkingLotResponseModel>> {
            override fun onSuccess(result: List<ParkingLotResponseModel>) {
                _listParking.value = result
            }

            override fun onFailure(message: String) {

            }
        })
    }

    fun verifyLoggedUser() {
        val nome = securityPreferences.get("nome")
        val id = securityPreferences.get("id")

        _userLogged.value = (nome.isNotEmpty() && id.isNotEmpty())
    }
}