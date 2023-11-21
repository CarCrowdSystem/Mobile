package com.example.driver_ccs.data.remote.newCar

import android.content.Context
import com.example.driver_ccs.data.remote.BaseRepository
import com.example.driver_ccs.data.remote.RetrofitClient
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.CarModel
import com.example.driver_ccs.data.remote.model.response.CarListResponseModel
import com.example.driver_ccs.data.remote.model.response.CarResponseModel
import com.example.driver_ccs.data.remote.newCar.network.INewCarService

class NewCarRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(INewCarService::class.java)

    fun getCarData(plate: String, listener: ApiListener<CarResponseModel>){
        executeCall(remote.getCarData(plate), listener)
    }

    fun registeCar(placa: String, marca: String, modelo: String, id_cliente: String, listener: ApiListener<Unit>) {
        executeCall(remote.registerCar(CarModel(placa, marca, modelo, id_cliente)), listener )
    }

    fun getCars(idCliente: Int, listener: ApiListener<List<CarListResponseModel>>) {
        executeCall(remote.getCars(idCliente), listener)
    }

    fun deleteCar(idCar: Int, listener: ApiListener<Unit>) {
        executeCall(remote.deleteCar(idCar), listener)
    }
}