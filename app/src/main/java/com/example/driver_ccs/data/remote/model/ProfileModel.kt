package com.example.driver_ccs.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProfileModel(

    @SerializedName("nome")
    val nome: String,
    @SerializedName("telefone")
    val telefone: String,
    @SerializedName("cpf")
    val cpf: String,
    @SerializedName("email")
    val email: String
)
