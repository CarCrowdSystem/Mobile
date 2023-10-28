package com.example.driver_ccs.data.remote.model


import com.google.gson.annotations.SerializedName

data class CadastroModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("nome")
    val nome: String,
    @SerializedName("senha")
    val senha: String
)