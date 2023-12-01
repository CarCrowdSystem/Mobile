package com.example.driver_ccs.ui.historic

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.driver_ccs.data.remote.model.response.HistoricResponseModel
import com.example.driver_ccs.databinding.ItemHistoricBinding
import com.example.driver_ccs.extensions.showOrHide

class HistoricAdapter(private val viewModel: HistoricViewModel) :
    RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder>() {

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

                mbtSaida.showOrHide(historicList[position].status == "1")
                mbtDoCheckOut.showOrHide(historicList[position].status == "0" && !historicList[position].isCheckinDone)
                mbtReserva.showOrHide(historicList[position].status == "3")
                mbtCancel.showOrHide(historicList[position].status == "3")
                mbtCheckIn.showOrHide(historicList[position].status == "2")
                mbtCheckInDone.showOrHide(historicList[position].status == "0" && historicList[position].isCheckinDone)

                tvName.text = historicList[position].nome
                tvAddress.text = historicList[position].rua
                tvTimeSpent.text = historicList[position].hora
                tvDateValue.text = historicList[position].data
                tvTotalValue.text = historicList[position].valor
            }
        }
        holder.binding.mbtDoCheckOut.setOnClickListener {
            viewModel.checkout(historicList[position].placa)
            viewModel.getHistoric()
            notifyDataSetChanged()
        }
        holder.binding.mbtCancel.setOnClickListener {
            viewModel.delete(historicList[position].idReserva.toInt())
            viewModel.getHistoric()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = historicList.size

    fun updateHistoric(list: List<HistoricResponseModel>) {
        this.historicList = list
        notifyDataSetChanged()
    }
}