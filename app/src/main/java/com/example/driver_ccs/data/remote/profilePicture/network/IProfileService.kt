package com.example.driver_ccs.data.remote.profilePicture.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IProfileService {

    @GET("https://api.dicebear.com/7.x/initials/json")
    fun getDefaultPicture(
        @Query("seed") name: String
    ): Call<Any>
}