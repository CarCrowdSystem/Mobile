package com.example.driver_ccs.data.remote.historic.network

import com.example.driver_ccs.data.remote.model.response.HistoricResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IHistoricService {

    @GET("clientes/historico")
    fun getHistoric (
        @Query("id") id: Int
    ): Call<List<HistoricResponseModel>>
}