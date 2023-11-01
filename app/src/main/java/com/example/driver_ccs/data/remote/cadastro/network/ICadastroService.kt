package com.example.driver_ccs.data.remote.cadastro.network

import com.example.driver_ccs.data.remote.model.CadastroModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ICadastroService {

    @POST("clientes")
    fun register (
        @Body person: CadastroModel
    ): Call<CadastroModel>
}