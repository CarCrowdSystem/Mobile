package com.example.driver_ccs.data.remote.model

import com.google.gson.annotations.SerializedName

data class CarModel(
    @SerializedName("placa")
    val placa: String,
    @SerializedName("marca")
    val marca: String,
    @SerializedName("modelo")
    val modelo: String,
    @SerializedName("id_cliente")
    val id_cliente: String
)
