package com.example.driver_ccs.ui.cadastro

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.driver_ccs.data.remote.cadastro.CadastroRepository
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.CadastroModel
import com.example.driver_ccs.data.remote.model.ValidationModel

class CadastroViewModel(application: Application): AndroidViewModel(application) {

    private val cadastroRepository = CadastroRepository(application.applicationContext)

    private val _user = MutableLiveData<ValidationModel>()
    val user : LiveData<ValidationModel> = _user

    fun cadastro(name: String, email: String, senha: String) {
        cadastroRepository.cadastro(name, email, senha, object : ApiListener<CadastroModel> {
            override fun onSuccess(result: CadastroModel) {

                _user.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _user.value = ValidationModel(message)
            }
        })
    }
}