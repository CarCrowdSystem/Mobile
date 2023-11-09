package com.example.driver_ccs.ui.historico

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.historic.HistoricRepository
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.response.HistoricResponseModel

class HistoricViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val historicRepository = HistoricRepository(application.applicationContext)

    private var _historicList = MutableLiveData<List<HistoricResponseModel>>()
    val historicList : LiveData<List<HistoricResponseModel>> = _historicList

    fun getHistoric() {
        val id = securityPreferences.get("id").toInt()
        historicRepository.getHistoric(id, object : ApiListener<List<HistoricResponseModel>> {
            override fun onSuccess(result: List<HistoricResponseModel>) {
                _historicList.value = result
            }

            override fun onFailure(message: String) {
            }
        })
    }
}