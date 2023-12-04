package com.example.driver_ccs.ui.parkingLot

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.ValidationModel
import com.example.driver_ccs.data.remote.parkingLots.ParkingLotRepository

class ParkingLotViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val parkingLotRepository = ParkingLotRepository(application.applicationContext)

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var _status = MutableLiveData<ValidationModel?>()
    val status: LiveData<ValidationModel?> = _status

    fun makeReservation(plate: String, idEstacionamento: Int, dataReserva: String, horaReserva: String) {
        _isLoading.value = true
        parkingLotRepository.makeReservation(plate, idEstacionamento, dataReserva, horaReserva, object : ApiListener<Unit> {
            override fun onSuccess(result: Unit) {
                _isLoading.value = false
                _status.value = ValidationModel()
                Log.d("***makeReservation", "success")
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