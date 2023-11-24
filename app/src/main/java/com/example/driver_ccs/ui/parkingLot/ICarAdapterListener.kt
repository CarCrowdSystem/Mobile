package com.example.driver_ccs.ui.parkingLot


interface ICarAdapterListener {
    fun onCarSelected(plate: String, position: Int)
}