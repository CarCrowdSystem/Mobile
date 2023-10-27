package com.example.driver_ccs.data.remote.login.network

import com.example.driver_ccs.data.remote.model.LoginModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ILoginService {

    @POST("clientes/login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("senha") password: String
    ): Call<LoginModel>
}