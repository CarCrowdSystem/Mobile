package com.example.driver_ccs.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.driver_ccs.data.remote.model.response.ParkingLotResponseModel
import com.example.driver_ccs.databinding.ItemEstacionamantosBinding

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ParkingLotViewHolder>() {

    private var parkingLotsList = emptyList<ParkingLotResponseModel>()

    class ParkingLotViewHolder(val binding: ItemEstacionamantosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingLotViewHolder {
        val item = ItemEstacionamantosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParkingLotViewHolder(item)
    }

    override fun onBindViewHolder(holder: ParkingLotViewHolder, position: Int) {
        holder.binding.apply {
            tvName.text = parkingLotsList[position].nome
            tvAddress.text = parkingLotsList[position].endereco
            tvPriceValue.text = parkingLotsList[position].primeira_hora
            tvSpotsAvailableValue.text = parkingLotsList[position].vagas_cheias

            mbtSeeMore.setOnClickListener {
                val action = HomeFragmentDirections.actionNavHomeToNavParking(parkingLotsList[position])
                it.findNavController().navigate(action)
            }
        }

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionNavHomeToNavParking(parkingLotsList[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = parkingLotsList.size

    fun update(list: List<ParkingLotResponseModel>) {
        this.parkingLotsList = list
        notifyDataSetChanged()
    }

}