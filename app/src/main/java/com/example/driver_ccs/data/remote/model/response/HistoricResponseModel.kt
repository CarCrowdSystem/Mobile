package com.example.driver_ccs.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class HistoricResponseModel(
    @SerializedName("idReserva")
    val idReserva: String,
    @SerializedName("nome")
    val nome: String,
    @SerializedName("rua")
    val rua: String,
    @SerializedName("data")
    val data: String,
    @SerializedName("hora")
    val hora: String,
    @SerializedName("valor")
    val valor: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("placa")
    val placa: String,
    @SerializedName("checkinDone")
    val isCheckinDone: Boolean
)
