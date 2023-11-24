package com.example.driver_ccs.data.remote.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ParkingLotResponseModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("nome")
    val nome: String,
    @SerializedName("endereco")
    val endereco: String,
    @SerializedName("cep")
    val cep: String,
    @SerializedName("total_vaga")
    val total_vagas: String,
    @SerializedName("vagas_cheias")
    val vagas_cheias: String,
    @SerializedName("diaria")
    val diaria: String,
    @SerializedName("primeira_hora")
    val primeira_hora: String,
    @SerializedName("hora_adicional")
    val hora_adicional: String
) : Serializable