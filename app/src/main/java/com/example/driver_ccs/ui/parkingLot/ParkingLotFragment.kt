package com.example.driver_ccs.ui.parkingLot

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentParkingLotDetailBinding
import com.example.driver_ccs.extensions.hide
import com.example.driver_ccs.extensions.show
import com.example.driver_ccs.extensions.showOrHide
import com.example.driver_ccs.extensions.viewBinding
import com.example.driver_ccs.ui.novoVeiculo.NewCarViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ParkingLotFragment : Fragment(), DatePickerDialog.OnDateSetListener, ICarAdapterListener {

    private val args: ParkingLotFragmentArgs by navArgs()
    private val carViewModel: NewCarViewModel by viewModels()
    private val viewModel: ParkingLotViewModel by viewModels()
    private val binding: FragmentParkingLotDetailBinding by viewBinding()
    private val adapter: CarAdapter by lazy { CarAdapter(this) }

    private var plate: String = ""
    private var dueDate = ""
    private var selectedTime = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carViewModel.getCarsList()
        displayData()
        setListener()
        observe()
        setupRecyclerView()
    }

    private fun setListener() {
        binding.btnSelectDate.setOnClickListener {
            handleDate()
        }
        binding.btnHourEntrance.setOnClickListener {
            handleDate()
        }
        binding.btnMakeReservation.setOnClickListener {
            if(plate.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Você deve selecionar um carro para fazer a reserva!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                if(dueDate.isEmpty() && selectedTime.isEmpty()) {
                    Snackbar.make(
                        binding.root,
                        "Você deve selecionar a data e hora da reserva!",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    viewModel.makeReservation(plate, args.parkingInfo.id.toInt(), dueDate, selectedTime)
                }
            }
        }
    }

    override fun onCarSelected(plate: String, id: Int) {
        this.plate = plate
    }

    private fun displayData() {
        binding.tvParkingName.text = args.parkingInfo.nome
        binding.tvParkingAddress.text = args.parkingInfo.endereco
        binding.tvHoursAndValues.text =
            getString(
                R.string.label_values_details,
                args.parkingInfo.primeira_hora,
                args.parkingInfo.hora_adicional,
                args.parkingInfo.diaria
            )
        binding.tvSpotsAvailableValue.text =
            getString(
                R.string.label_amount_of_spots,
                args.parkingInfo.vagas_cheias,
                args.parkingInfo.total_vagas
            )
    }

    private fun observe() {
        carViewModel.carsListData.observe(viewLifecycleOwner) {
            adapter.updateCarList(it)
        }
        viewModel.status.observe(viewLifecycleOwner) {
            if(it.showStatus()) {
                findNavController().navigate(R.id.action_nav_parking_to_nav_success_reservation)
            } else {
                findNavController().navigate(R.id.action_nav_parking_to_nav_error_reservation)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if(isLoading) {
                binding.pbLoading.show()
                makeView(isLoading)
            } else {
                binding.pbLoading.hide()
                makeView(isLoading)
            }
        }
    }

    private fun handleDate() {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val hour = calender.get(Calendar.HOUR_OF_DAY)
        val minute = calender.get(Calendar.MINUTE)

        DatePickerDialog(requireContext(), this, year, month, day).show()

        TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                onTimeSet(hourOfDay, minute)
            },
            hour,
            minute,
            true
        ).show()
    }

    private fun onTimeSet(hourOfDay: Int, minute: Int) {
        val seconds = 0
        selectedTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", hourOfDay, minute, seconds)
        binding.btnHourEntrance.text = selectedTime
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        try {
            dueDate = dateFormat.format(calendar.time)

            binding.btnSelectDate.text = dueDate
        } catch (e: Exception) {
            Log.e("DatePicker", "Error formatting date: ${e.message}")
        }
    }

    private fun setupRecyclerView() {
        binding.rvCarList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCarList.adapter = adapter
    }

    private fun makeView(isLoading: Boolean){
        binding.apply {
            tvParkingName.showOrHide(isLoading)
            tvParkingAddress.showOrHide(isLoading)
            vLayoutTop.showOrHide(isLoading)
            tvLabelValues.showOrHide(isLoading)
            tvHoursAndValues.showOrHide(isLoading)
            vLayoutMiddle.showOrHide(isLoading)
            tvSpotsAvailableValue.showOrHide(isLoading)
            tvLabelSpotAvailable.showOrHide(isLoading)
            rvCarList.showOrHide(isLoading)
            btnMakeReservation.showOrHide(isLoading)
            btnHourEntrance.showOrHide(isLoading)
            btnSelectDate.showOrHide(isLoading)
        }
    }
}