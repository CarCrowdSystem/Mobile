package com.example.driver_ccs.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("nome")
    val nome: String,
    @SerializedName("id")
    var id: String
)
