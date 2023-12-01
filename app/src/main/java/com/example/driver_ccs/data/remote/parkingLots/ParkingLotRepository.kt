package com.example.driver_ccs.data.remote.parkingLots

import android.content.Context
import com.example.driver_ccs.data.remote.BaseRepository
import com.example.driver_ccs.data.remote.RetrofitClient
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.response.ParkingLotLatLongResponseModel
import com.example.driver_ccs.data.remote.model.response.ParkingLotResponseModel
import com.example.driver_ccs.data.remote.parkingLots.network.IParkinkLotService

class ParkingLotRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(IParkinkLotService::class.java)

    fun getParkingLots(listener: ApiListener<List<ParkingLotResponseModel>>){
        executeCall(remote.getParkingLots(), listener)
    }

    fun makeReservation(plate: String, idEstacionamento: Int, dataReserva: String, horaReserva: String, listener: ApiListener<Unit>) {
        executeCall(remote.makeReservation(plate, idEstacionamento, dataReserva, horaReserva), listener)
    }

    fun getParkingLotLatLong(cep: String, listener: ApiListener<List<ParkingLotLatLongResponseModel>>) {
        executeCall(remote.getParkingLotLatLong("json", cep, "Brazil", 1), listener)
    }
}