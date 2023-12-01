package com.example.driver_ccs.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.ParkingLotModel
import com.example.driver_ccs.data.remote.model.response.ParkingLotLatLongResponseModel
import com.example.driver_ccs.data.remote.model.response.ParkingLotResponseModel
import com.example.driver_ccs.data.remote.parkingLots.ParkingLotRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val parkingLotRepository = ParkingLotRepository(application.applicationContext)

    private val _listParking = MutableLiveData<List<ParkingLotResponseModel>>()
    val listParking: LiveData<List<ParkingLotResponseModel>> = _listParking

    private var _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    private var _parkingLotPosition = MutableLiveData<List<ParkingLotLatLongResponseModel>>()
    val parkingLotPosition: LiveData<List<ParkingLotLatLongResponseModel>> = _parkingLotPosition

    var parkingLotCepList: List<String> = emptyList()

    private val securityPreferences = SecurityPreferences(application.applicationContext)

   fun getParkingLots() {
        parkingLotRepository.getParkingLots(object : ApiListener<List<ParkingLotResponseModel>> {
            override fun onSuccess(result: List<ParkingLotResponseModel>) {
                _listParking.value = result
                var count = 0
                while (count < result.size) {
                    if (result.isNotEmpty()) {
                        parkingLotCepList = listOf(result[count].cep)
                        getParkingLotLatLong(result[count].cep)
                    }
                    count++
                }

            }

            override fun onFailure(message: String) {
            }
        })
    }

    fun getParkingLotLatLong(cep: String) {
//        var parkingLotCepList = arrayListOf("01310-928", "01311-940", "01418-970", "01311-300", "01310-928")

//        for(cep in parkingLotCepList) {
//            delay(5000)
            parkingLotRepository.getParkingLotLatLong(cep, object : ApiListener<List<ParkingLotLatLongResponseModel>> {
                    override fun onSuccess(result: List<ParkingLotLatLongResponseModel>) {
                        _parkingLotPosition.value = result
                        Log.d("***success maps", "$cep - ${_parkingLotPosition.value}")
                    }

                    override fun onFailure(message: String) {
                        Log.d("***failure maps", "$message")
                    }
            })
//            delay(2000)
//        }
    }

    fun verifyLoggedUser() {
        val nome = securityPreferences.get("nome")
        val id = securityPreferences.get("id")

        _userLogged.value = (nome.isNotEmpty() && id.isNotEmpty())
    }
}