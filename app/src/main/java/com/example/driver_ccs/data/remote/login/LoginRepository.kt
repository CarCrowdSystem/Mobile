package com.example.driver_ccs.data.remote.login

import com.example.driver_ccs.utils.Resource
import com.example.driver_ccs.data.remote.login.network.LoginService

class LoginRepository(private val service: LoginService) {

    suspend fun login(email: String, password: String) : String {
        val resource = service.login(email, password)

        if(resource is Resource.Success) {
            return resource.data
        }

        return ""
    }
}