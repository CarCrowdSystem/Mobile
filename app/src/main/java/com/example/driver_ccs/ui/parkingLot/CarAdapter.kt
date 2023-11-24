package com.example.driver_ccs.ui.parkingLot

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.driver_ccs.data.remote.model.response.CarListResponseModel
import com.example.driver_ccs.databinding.ItemNovoVeiculoBinding
import com.example.driver_ccs.extensions.toggle

class CarAdapter(private val listener: ICarAdapterListener): RecyclerView.Adapter<CarAdapter.CarViewHolder>(){

    private var carsList = listOf<CarListResponseModel>()
    private var selectedPosition = RecyclerView.NO_POSITION

    class CarViewHolder(val binding: ItemNovoVeiculoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val item = ItemNovoVeiculoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(item)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.binding.apply {
            ivLixo.toggle(false)
            cbCarSelected.toggle(true)

            if (carsList.isNotEmpty()) {
                tvMarca.text = carsList[position].marca
                tvModelo.text = carsList[position].modelo
                tvPlaca.text = carsList[position].placa

                cbCarSelected.isChecked = position == selectedPosition

                cbCarSelected.setOnClickListener {
                    handleItemClick(position)
                }
            }
        }

        holder.itemView.setOnClickListener {
            holder.binding.cbCarSelected.isChecked = true
            handleItemClick(position)
        }
    }

    private fun handleItemClick(position: Int) {
        if (selectedPosition != position) {
            notifyItemChanged(selectedPosition)
            selectedPosition = position
            notifyItemChanged(position)

            listener.onCarSelected(carsList[position].placa, position)
        }
    }

    override fun getItemCount() = carsList.size

    fun updateCarList(list: List<CarListResponseModel>) {
        this.carsList = list
        notifyDataSetChanged()
    }
}