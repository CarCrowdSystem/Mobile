package com.example.driver_ccs.data.remote.cars.network

import com.example.driver_ccs.data.remote.model.CarModel
import com.example.driver_ccs.data.remote.model.response.CarListResponseModel
import com.example.driver_ccs.data.remote.model.response.CarResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface INewCarService {

    //new key
    //4ec3fdb4ebe22e46e14b6443ba58283d
    @GET("https://wdapi2.com.br/consulta/{placa}/b15fd7bc1c6c3e641875aa093cbaf367")
    fun getCarData(
        @Path("placa") placa: String
    ): Call<CarResponseModel>

    @POST("veiculo/mobile")
    fun registerCar(
        @Body car: CarModel
    ): Call<Unit>

    @GET("veiculo/all")
    fun getCars(
        @Query("id") id: Int
    ): Call<List<CarListResponseModel>>

    @DELETE("veiculo")
    fun deleteCar(
        @Query("id") id: Int
    ): Call<Unit>
}