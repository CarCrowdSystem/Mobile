package com.example.driver_ccs.ui.historico

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.driver_ccs.databinding.ItemHistoricBinding

class HistoricAdapter() : RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder>() {

    private var historicList = emptyList<ParkinglLot>()

    class HistoricViewHolder(val binding: ItemHistoricBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricViewHolder {
        val item = ItemHistoricBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoricViewHolder(item)
    }

    override fun onBindViewHolder(holder: HistoricViewHolder, position: Int) {
        holder.binding.apply {
            if(!historicList.isNullOrEmpty()){
                tvName.text = historicList[position].nome
                tvAddress.text = historicList[position].endereco
                tvTimeSpendIn.text = historicList[position].tempo
                tvSpotTag.text = historicList[position].vaga
                tvTotal.text = historicList[position].total
            }
        }
    }

    override fun getItemCount() = historicList.size

    fun updateHistoric(list: List<ParkinglLot>){
        this.historicList = list
        notifyDataSetChanged()
    }

}