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

    private var _alert = MutableLiveData<ValidationModel>()
    val alert : LiveData<ValidationModel> = _alert

    fun makeReservation(plate: String, idEstacionamento: Int, dataReserva: String, horaReserva: String) {
        Log.d("***makeReservation" ,"$plate, $idEstacionamento, $dataReserva, $horaReserva")
        parkingLotRepository.makeReservation(plate, idEstacionamento, dataReserva, horaReserva, object : ApiListener<Unit> {
            override fun onSuccess(result: Unit) {
                _alert.value = ValidationModel("Reserva feita com sucesso!")
            }

            override fun onFailure(message: String) {}
        })
    }

}