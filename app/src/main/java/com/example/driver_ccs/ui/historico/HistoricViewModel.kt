package com.example.driver_ccs.ui.historico

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoricViewModel: ViewModel() {

    private var _historicList = MutableLiveData<List<String>>()
    val historicList : LiveData<List<String>> = _historicList

    fun getHistoric() {
        _historicList.value = listOf("Nome_um", "Nome_dois", "Nome_três", "Nome_quatro", "Nome_cinco")
    }

}