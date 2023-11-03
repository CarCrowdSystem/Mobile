package com.example.driver_ccs.data.remote.login.network

import com.example.driver_ccs.data.remote.model.LoginModel
import com.example.driver_ccs.data.remote.model.response.LoginResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ILoginService {

    @POST("clientes/login")
    fun login(
        @Body login: LoginModel
    ): Call<LoginResponseModel>
}