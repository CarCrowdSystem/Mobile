package com.example.driver_ccs.data.remote.editPassword.network

import com.example.driver_ccs.data.remote.model.CadastroModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IEditPasswordService {

    @POST("clientes")
    fun editPassword (
//        Algum metodo
    ): Call<Unit>
}