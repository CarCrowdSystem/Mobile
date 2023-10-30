package com.example.driver_ccs.ui.novo_veiculo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.CarResponseModel
import com.example.driver_ccs.data.remote.model.ValidationModel
import com.example.driver_ccs.data.remote.newCar.NewCarRepository

class NewCarViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val newCarRepository = NewCarRepository(application.applicationContext)

    private var _carData = MutableLiveData<CarResponseModel>()
    val carData: LiveData<CarResponseModel> = _carData

    fun getCarData(plate: String) {
        newCarRepository.getCarData(plate, object : ApiListener<CarResponseModel> {
            override fun onSuccess(result: CarResponseModel) {
                _carData.value = result
            }

            override fun onFailure(message: String) {
                Log.d("***onFailure", message)
            }
        })
    }
}