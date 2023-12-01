package com.example.driver_ccs.ui.historic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.historic.HistoricRepository
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.ValidationModel
import com.example.driver_ccs.data.remote.model.response.HistoricResponseModel

class HistoricViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val historicRepository = HistoricRepository(application.applicationContext)

    private var _historicList = MutableLiveData<List<HistoricResponseModel>>()
    val historicList : LiveData<List<HistoricResponseModel>> = _historicList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var _alert = MutableLiveData<ValidationModel>()
    val alert : LiveData<ValidationModel> = _alert

    fun getHistoric() {
        _isLoading.value = true
        val id = securityPreferences.get("id").toInt()
        historicRepository.getHistoric(id, object : ApiListener<List<HistoricResponseModel>> {
            override fun onSuccess(result: List<HistoricResponseModel>) {
                _isLoading.value = false
                _historicList.value = result
            }

            override fun onFailure(message: String) {
                _isLoading.value = false
            }
        })
    }

    fun checkout(plate: String) {
        historicRepository.checkout(plate, object : ApiListener<Unit> {
            override fun onSuccess(result: Unit) {
                _alert.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _alert.value = ValidationModel("Erro ao pedir checkout!")
            }

        })
    }

    fun delete(idReserva: Int) {
        historicRepository.deleteReservation(idReserva, object : ApiListener<Unit> {
            override fun onSuccess(result: Unit) {
                _alert.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _alert.value = ValidationModel("Erro ao cancelar reserva!")
            }
        })
    }
}