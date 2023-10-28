package com.example.driver_ccs.data.remote.cadastro.network

import com.example.driver_ccs.data.remote.model.CadastroModel
import com.example.driver_ccs.data.remote.model.LoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ICadastroService {

    @POST("clientes")
    fun cadastro(
        @Body person: CadastroModel
    ): Call<CadastroModel>
}