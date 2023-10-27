package com.example.driver_ccs.data.remote.cadastro.network

import com.example.driver_ccs.data.remote.model.CadastroModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ICadastroService {

    @POST("clientes")
    @FormUrlEncoded
    fun cadastro(
        @Field("nome") nome: String,
        @Field("email") email: String,
        @Field("senha") password: String
    ): Call<CadastroModel>
}