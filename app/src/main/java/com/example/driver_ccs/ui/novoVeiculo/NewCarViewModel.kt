package com.example.driver_ccs.ui.novoVeiculo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.response.CarResponseModel
import com.example.driver_ccs.data.remote.model.ValidationModel
import com.example.driver_ccs.data.remote.model.response.CarListResponseModel
import com.example.driver_ccs.data.remote.newCar.NewCarRepository

class NewCarViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val newCarRepository = NewCarRepository(application.applicationContext)

    private var _carData = MutableLiveData<CarResponseModel>()
    val carData: LiveData<CarResponseModel> = _carData

    private var _carsListData = MutableLiveData<List<CarListResponseModel>>()
    val carsListData : LiveData<List<CarListResponseModel>> = _carsListData

    private var _alert = MutableLiveData<ValidationModel>()
    val alert : LiveData<ValidationModel> = _alert

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getCarData(plate: String) {
        newCarRepository.getCarData(plate, object : ApiListener<CarResponseModel> {
            override fun onSuccess(result: CarResponseModel) {
                _carData.value = result
            }

            override fun onFailure(message: String) {
                _alert.value = ValidationModel(message)
            }
        })
    }

    fun registerCar(placa: String, modelo: String, marca: String){
        _isLoading.value = true
        val id = securityPreferences.get("id")
        newCarRepository.registeCar(placa, modelo, marca, id, object : ApiListener<Unit> {
            override fun onSuccess(result: Unit) {
                _isLoading.value = false
                _alert.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _isLoading.value = false
                _alert.value = ValidationModel(message)
            }
        })
    }

    fun getCarsList() {
        _isLoading.value = true
        val id = securityPreferences.get("id")
        newCarRepository.getCars(id.toInt(), object : ApiListener<List<CarListResponseModel>> {
            override fun onSuccess(result: List<CarListResponseModel>) {
                _isLoading.value = false
                _carsListData.value = result
            }

            override fun onFailure(message: String) {
                _isLoading.value = false
            }
        })
    }
}