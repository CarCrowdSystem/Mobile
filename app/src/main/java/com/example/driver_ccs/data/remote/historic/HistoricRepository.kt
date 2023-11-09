package com.example.driver_ccs.data.remote.historic

import android.content.Context
import com.example.driver_ccs.data.remote.BaseRepository
import com.example.driver_ccs.data.remote.RetrofitClient
import com.example.driver_ccs.data.remote.historic.network.IHistoricService
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.CadastroModel
import com.example.driver_ccs.data.remote.model.response.HistoricResponseModel

class HistoricRepository(context: Context) : BaseRepository(context){

    private val remote = RetrofitClient.getService(IHistoricService::class.java)

    fun getHistoric(id_cliente: Int, listener: ApiListener<List<HistoricResponseModel>>) {
        executeCall(remote.getHistoric(id_cliente), listener)
    }
}