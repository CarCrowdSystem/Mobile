package com.example.driver_ccs.ui.historico

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.driver_ccs.data.remote.model.response.HistoricResponseModel
import com.example.driver_ccs.databinding.ItemHistoricBinding
import com.example.driver_ccs.extensions.toggle

class HistoricAdapter() : RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder>() {

    private var historicList = emptyList<HistoricResponseModel>()

    class HistoricViewHolder(val binding: ItemHistoricBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricViewHolder {
        val item = ItemHistoricBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoricViewHolder(item)
    }

    override fun onBindViewHolder(holder: HistoricViewHolder, position: Int) {
        holder.binding.apply {
            if (historicList.isNotEmpty()) {
                when(historicList[position].status) {
                    "1" ->  mbtSaida.toggle(true)
                    "2" -> mbtCheckOut.toggle(true)
                    else -> mbtEntrada.toggle(true)
                }

                tvName.text = historicList[position].nome
                tvAddress.text = historicList[position].rua
                tvTimeSpent.text = historicList[position].hora
                tvDateValue.text = historicList[position].data
                tvTotalValue.text = historicList[position].valor
            }
        }
    }

    override fun getItemCount() = historicList.size

    fun updateHistoric(list: List<HistoricResponseModel>) {
        this.historicList = list
        notifyDataSetChanged()
    }
}