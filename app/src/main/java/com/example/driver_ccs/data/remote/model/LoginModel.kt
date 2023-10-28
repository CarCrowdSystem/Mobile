package com.example.driver_ccs.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginModel (
    @SerializedName("email")
    val email: String,
    @SerializedName("senha")
    var senha: String
)
