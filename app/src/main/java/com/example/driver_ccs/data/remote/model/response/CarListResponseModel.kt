package com.example.driver_ccs.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CarListResponseModel(
    @SerializedName("placa")
    val placa: String,
    @SerializedName("marca")
    val marca: String,
    @SerializedName("modelo")
    val modelo: String,
    @SerializedName("id_carro")
    val id_carro: String
)
