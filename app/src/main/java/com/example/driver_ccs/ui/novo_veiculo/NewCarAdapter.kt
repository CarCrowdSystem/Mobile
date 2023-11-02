package com.example.driver_ccs.ui.novo_veiculo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.driver_ccs.databinding.ItemNovoVeiculoBinding

class NewCarAdapter() : RecyclerView.Adapter<NewCarAdapter.NewCarViewHolder>() {

    private var carsList = listOf<Cars>()

    class NewCarViewHolder(val binding: ItemNovoVeiculoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewCarViewHolder {
        val item = ItemNovoVeiculoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewCarViewHolder(item)
    }

    override fun onBindViewHolder(holder: NewCarViewHolder, position: Int) {
        holder.binding.apply {
            if(carsList.isNotEmpty()){
                tvMarca.text = carsList[position].marca
                tvModelo.text = carsList[position].modelo
                tvPlaca.text = carsList[position].placa
            }
        }
    }

    override fun getItemCount() = carsList.size

    fun updateCarList(list: List<Cars>) {
        this.carsList = list
        notifyDataSetChanged()
    }
}