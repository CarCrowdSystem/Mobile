package com.example.driver_ccs.data.remote.model


import com.google.gson.annotations.SerializedName

data class CarResponseModel(
    @SerializedName("MARCA")
    val marca: String,
    @SerializedName("MODELO")
    val modelo: String
)