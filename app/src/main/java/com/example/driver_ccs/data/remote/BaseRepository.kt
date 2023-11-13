package com.example.driver_ccs.data.remote

import android.content.Context
import android.util.Log
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {

//    private fun failResponse(str: String): String = Gson().fromJson(str, String::class.java)

    private fun failResponse(str: String): String {
        return try {
            Gson().fromJson(str, String::class.java)
        } catch (e: JsonSyntaxException) {
            "Failed to parse JSON response: $str"
        }
    }

    fun <T> executeCall(call: Call<T>, listener: ApiListener<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() in 200..299) {
                    response.body()?.let { listener.onSuccess(it) }
                } else if (response.code() in 400..499){
                    listener.onFailure(failResponse("Client Error"))
                } else if (response.code() in 500..599){
                    listener.onFailure(failResponse("Server error"))
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