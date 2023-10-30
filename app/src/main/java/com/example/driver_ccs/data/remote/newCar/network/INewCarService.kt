package com.example.driver_ccs.data.remote.newCar.network

import com.example.driver_ccs.data.remote.model.CarResponseModel
import com.example.driver_ccs.data.remote.model.LoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface INewCarService {

    @GET("https://wdapi2.com.br/consulta/{placa}/b15fd7bc1c6c3e641875aa093cbaf367")
    fun getCarData(
        @Path("placa") placa: String
    ): Call<CarResponseModel>
}