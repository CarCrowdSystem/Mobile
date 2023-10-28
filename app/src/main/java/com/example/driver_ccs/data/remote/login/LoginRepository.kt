package com.example.driver_ccs.data.remote.login

import android.content.Context
import com.example.driver_ccs.data.remote.BaseRepository
import com.example.driver_ccs.data.remote.RetrofitClient
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.login.network.ILoginService
import com.example.driver_ccs.data.remote.model.LoginModel

class LoginRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(ILoginService::class.java)

    fun login(email: String, password: String, listener: ApiListener<LoginModel>) {
        executeCall(remote.login(LoginModel(email, password)), listener)
    }
}