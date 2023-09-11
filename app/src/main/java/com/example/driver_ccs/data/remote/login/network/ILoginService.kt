package com.example.driver_ccs.data.remote.login.network

import retrofit2.Response
import retrofit2.http.POST

interface ILoginService {

    @POST("login")
    suspend fun login(): Response<String>
}