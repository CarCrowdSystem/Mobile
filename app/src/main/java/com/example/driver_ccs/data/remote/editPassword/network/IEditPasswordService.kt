package com.example.driver_ccs.data.remote.editPassword.network

import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.Query

interface IEditPasswordService {

    @PATCH("clientes/alterar-senha")
    fun editPassword (
        @Query("id") id : Int,
        @Query("oldPass") oldPassword : String,
        @Query("newPass") newPassword : String
    ): Call<Unit>
}