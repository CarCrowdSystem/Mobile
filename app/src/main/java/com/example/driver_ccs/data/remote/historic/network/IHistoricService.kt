package com.example.driver_ccs.data.remote.historic.network

import com.example.driver_ccs.data.remote.model.response.HistoricResponseModel
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IHistoricService {

    @GET("clientes/historico")
    fun getHistoric (
        @Query("id") id: Int
    ): Call<List<HistoricResponseModel>>

    @POST("historicos/processar")
    fun checkout(
        @Query("placa") placa : String
    ): Call<Unit>

    @DELETE("historicos/reserva")
    fun deleteReservation(
        @Query("id") id_reserva : Int
    ): Call<Unit>

}