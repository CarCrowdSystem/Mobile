package com.example.driver_ccs.data.remote

import android.content.Context
import com.example.driver_ccs.R
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {

    private fun failResponse(str: String): String = Gson().fromJson(str, String::class.java)

    fun <T> executeCall(call: Call<T>, listener: ApiListener<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == 200) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onFailure(failResponse("Error"))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onFailure("Um erro inesperado ocorreu. Tente novamente mais tarde.")
            }
        })
    }

}