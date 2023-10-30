package com.example.driver_ccs.data.remote.newCar

import android.content.Context
import com.example.driver_ccs.data.remote.BaseRepository
import com.example.driver_ccs.data.remote.RetrofitClient
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.CarResponseModel
import com.example.driver_ccs.data.remote.model.LoginModel
import com.example.driver_ccs.data.remote.newCar.network.INewCarService

class NewCarRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(INewCarService::class.java)

    fun getCarData(plate: String, listener: ApiListener<CarResponseModel>){
        executeCall(remote.getCarData(plate), listener)
    }
}