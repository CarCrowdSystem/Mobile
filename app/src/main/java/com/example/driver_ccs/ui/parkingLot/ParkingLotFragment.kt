package com.example.driver_ccs.ui.parkingLot

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.driver_ccs.databinding.FragmentParkingLotDetailBinding
import com.example.driver_ccs.extensions.viewBinding
import java.util.Calendar

class ParkingLotFragment : Fragment() {

    private val args: ParkingLotFragmentArgs by navArgs()
    private val binding: FragmentParkingLotDetailBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvParkingName.text = args.parkingInfo.nome
        binding.tvParkingAddress.text = args.parkingInfo.endereco
        setListener()
    }

    private fun setListener() {
        binding.btnSelectDate.setOnClickListener {
            handleDate()
        }
    }

    private fun handleDate() {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(requireContext(), null, year, month, day).show()
    }
}