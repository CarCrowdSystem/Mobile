package com.example.driver_ccs.data.remote.cadastro

import android.content.Context
import android.util.Log
import com.example.driver_ccs.data.remote.BaseRepository
import com.example.driver_ccs.data.remote.RetrofitClient
import com.example.driver_ccs.data.remote.cadastro.network.ICadastroService
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.CadastroModel

class CadastroRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(ICadastroService::class.java)

    fun register(nome: String, email: String, senha: String, listener: ApiListener<Unit>) {
        executeCall(remote.register(CadastroModel(email, nome, senha)), listener)
    }
}