package com.example.driver_ccs.ui.cadastro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driver_ccs.data.remote.cadastro.CadastroRepository
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.CadastroModel
import com.example.driver_ccs.data.remote.model.ValidationModel

class CadastroViewModel(application: Application): AndroidViewModel(application) {

    private val cadastroRepository = CadastroRepository(application.applicationContext)

    private val _user = MutableLiveData<ValidationModel>()
    val user : LiveData<ValidationModel> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun register(name: String, email: String, senha: String) {
        _isLoading.value = true

        cadastroRepository.register(name, email, senha, object : ApiListener<CadastroModel> {
            override fun onSuccess(result: CadastroModel) {
                _isLoading.value = false
                _user.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _isLoading.value = false
                _user.value = ValidationModel(message)
            }
        })
    }
}