package com.example.driver_ccs.data.remote.parkingLots.network

import com.example.driver_ccs.data.remote.model.response.ParkingLotLatLongResponseModel
import com.example.driver_ccs.data.remote.model.response.ParkingLotResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IParkinkLotService {

    @GET("estacionamentos/all")
    fun getParkingLots(): Call<List<ParkingLotResponseModel>>

    @POST("historicos/reserva")
    fun makeReservation(
        @Query("placa") place: String,
        @Query("idEstacionamento") idEstacionamento: Int,
        @Query("dataReserva") dataReserva: String,
        @Query("horaReserva") horaReserva: String
    ): Call<Unit>

    @GET("https://nominatim.openstreetmap.org/search")
    fun getParkingLotLatLong(
        @Query("format") format: String,
        @Query("postalcode") cep: String,
        @Query("country") country: String,
        @Query("address details") addressdetails: Int
    ): Call<List<ParkingLotLatLongResponseModel>>
}