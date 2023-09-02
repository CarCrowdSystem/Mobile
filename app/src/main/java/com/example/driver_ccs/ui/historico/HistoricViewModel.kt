package com.example.driver_ccs.ui.historico

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoricViewModel: ViewModel() {

    private var _historicList = MutableLiveData<List<ParkinglLot>>()
    val historicList : LiveData<List<ParkinglLot>> = _historicList

    fun getHistoric() {
        _historicList.value =
            listOf(
                ParkinglLot("Car park","Rua consolação, 123 - Bairro, Cidade, SP", "06h00","A01","R$ 20,00"),
                ParkinglLot("Car park","Rua consolação, 123 - Bairro, Cidade, SP", "06h00","A01","R$ 20,00"),
                ParkinglLot("Car park","Rua consolação, 123 - Bairro, Cidade, SP", "06h00","A01","R$ 20,00"),
                ParkinglLot("Car park","Rua consolação, 123 - Bairro, Cidade, SP", "06h00","A01","R$ 20,00"),
                ParkinglLot("Car park","Rua consolação, 123 - Bairro, Cidade, SP", "06h00","A01","R$ 20,00")
            )
    }
}

data class ParkinglLot(val nome: String, val endereco: String, val tempo: String, val vaga: String, val total: String)