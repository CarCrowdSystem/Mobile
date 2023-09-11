package com.example.driver_ccs.data.remote

import com.example.driver_ccs.data.ExternalRoutes
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){

    companion object {
        private lateinit var INSTANCE: Retrofit
        private val BASE_URL = ExternalRoutes.ccsApi

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(Interceptor {
                val request = it.request()
                    .newBuilder()
                    .build()
                return@Interceptor it.proceed(request)
            })

            if(!::INSTANCE.isInitialized) {
                synchronized(RetrofitClient::class) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(OkHttpClient.Builder().build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T{
            return getRetrofitInstance().create(serviceClass)
        }
    }
}