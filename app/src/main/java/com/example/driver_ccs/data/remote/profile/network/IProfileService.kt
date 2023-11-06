package com.example.driver_ccs.data.remote.profile.network

import com.example.driver_ccs.data.remote.model.ProfileModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface IProfileService {

    @GET("https://api.dicebear.com/7.x/initials/json")
    fun getDefaultPicture(
        @Query("seed") name: String
    ): Call<Any>

    @PATCH("clientes/{id}")
    fun updateProfile(
        @Path("id") id: Int,
        @Body profile : ProfileModel
    ): Call<Unit>
}