package com.example.driver_ccs.data.remote.listener

interface ApiListener<T>{
    fun onSuccess(result: T)
    fun onFailure(message: String)
}