package com.example.driver_ccs.ui.novo_veiculo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.SecurityPreferences
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.CarResponseModel
import com.example.driver_ccs.data.remote.model.ValidationModel
import com.example.driver_ccs.data.remote.newCar.NewCarRepository

class NewCarViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val newCarRepository = NewCarRepository(application.applicationContext)

    private var _carData = MutableLiveData<CarResponseModel>()
    val carData: LiveData<CarResponseModel> = _carData

    private var _carsListData = MutableLiveData<List<Cars>>()
    val carsListData : LiveData<List<Cars>> = _carsListData

    private var _alert  = MutableLiveData<ValidationModel>()
    val alert : LiveData<ValidationModel> = _alert

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
        val id = securityPreferences.get("id")
        Log.d("***registeCar vm","$placa, $modelo, $marca, $id")
        newCarRepository.registeCar(placa, modelo, marca, id, object : ApiListener<Unit> {
            override fun onSuccess(result: Unit) {
                _alert.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _alert.value = ValidationModel(message)
            }
        })
    }

    fun getCarsList() {
        _carsListData.value =
            listOf(
                Cars("Nissan","Skyline","1234567"),
                Cars("Mitsubishi","Lancer","6969xxx"),
                Cars("Audi","R8","7654321")
            )
    }
}

data class Cars(val marca: String, val modelo: String, val placa: String)