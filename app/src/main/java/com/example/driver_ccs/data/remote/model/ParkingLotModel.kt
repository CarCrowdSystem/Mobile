package com.example.driver_ccs.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ParkingLotModel(
    @SerializedName("nome")
    val nome: String,
    @SerializedName("endereco")
    val endereco: String,
    @SerializedName("valorHora")
    val valorHora: String,
    @SerializedName("vagas")
    val vagasDisponiveis: String,
    @SerializedName("distancia")
    val distancia: String
) : Serializable
