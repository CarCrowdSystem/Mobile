package com.example.driver_ccs.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.driver_ccs.databinding.ItemEstacionamantosBinding

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ParkingLotViewHolder>() {

    private var parkingLotsList = emptyList<ParkinglLotHome>()

    class ParkingLotViewHolder(val binding: ItemEstacionamantosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingLotViewHolder {
        val item = ItemEstacionamantosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParkingLotViewHolder(item)
    }

    override fun onBindViewHolder(holder: ParkingLotViewHolder, position: Int) {
        holder.binding.apply {
            tvName.text = parkingLotsList[position].nome
            tvAddress.text = parkingLotsList[position].endereco
            tvPriceValue.text = parkingLotsList[position].valorHora
            tvSpotsAvailableValue.text = parkingLotsList[position].vagasDisponiveis
            tvDistanceValue.text = parkingLotsList[position].distancia
        }
    }

    override fun getItemCount() = parkingLotsList.size

    fun update(list: List<ParkinglLotHome>) {
        this.parkingLotsList = list
        notifyDataSetChanged()
    }

}