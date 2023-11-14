package com.example.driver_ccs.data.remote.editPassword

import android.content.Context
import com.example.driver_ccs.data.remote.BaseRepository
import com.example.driver_ccs.data.remote.RetrofitClient
import com.example.driver_ccs.data.remote.editPassword.network.IEditPasswordService
import com.example.driver_ccs.data.remote.listener.ApiListener

class EditPasswordRepository(context: Context): BaseRepository(context){

    private val remote = RetrofitClient.getService(IEditPasswordService::class.java)

    fun editPassWord(newPassWord: String, listener: ApiListener<Unit>) {
        executeCall(remote.editPassword(), listener)
    }
}