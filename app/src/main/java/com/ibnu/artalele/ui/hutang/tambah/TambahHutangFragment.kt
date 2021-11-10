package com.ibnu.artalele.ui.hutang.tambah

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.ibnu.artalele.data.entities.DebtEntity
import com.ibnu.artalele.databinding.TambahHutangFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.ConstValue.CALCULATOR_HUTANG
import com.ibnu.artalele.utils.ConstValue.DEBT_REQUEST_KEY
import com.ibnu.artalele.utils.ConstValue.DEBT_RESULT_KEY
import java.util.*

class TambahHutangFragment : Fragment() {

    private var date = ""
    private var startDate: Date? = null
    private var dueDate: Date? = null

    private val calendar: Calendar = Calendar.getInstance()

    private var total: String? = null

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(TambahHutangViewModel::class.java)
    }

    private var _bindingTambahHutangFragment: TambahHutangFragmentBinding? = null
    private val binding get() = _bindingTambahHutangFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingTambahHutangFragment =
            TambahHutangFragmentBinding.inflate(inflater, container, false)
        return _bindingTambahHutangFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(DEBT_REQUEST_KEY) { _, bundle ->
            total = ArtaLeleHelper.addRupiahToThousandAmountFromString(
                bundle.getString(
                    DEBT_RESULT_KEY
                ) ?: "0"
            )
            binding?.tvTotal?.text = total
        }

        initiateView()
        initiateButtonFunction()

    }

    private fun initiateView() {
        date = ArtaLeleHelper.getTodayDate()
        startDate = calendar.time
        binding?.tvDate?.text = date
    }

    private fun initiateButtonFunction() {

        binding?.btnSave?.setOnClickListener {
            saveDebt(it)
        }

        binding?.selectTotal?.setOnClickListener { view ->
            val action =
                TambahHutangFragmentDirections.actionTambahHutangFragmentToCalculatorFragment(
                    CALCULATOR_HUTANG
                )
            view.findNavController().navigate(action)
        }

    }

    private fun saveDebt(view: View) {
        var isBillable = true
        when (binding?.spinnerDueDuration?.selectedItemPosition) {
            0 -> {
                isBillable = false
            }
            1 -> {
                addDueDurationToTodayDate(3)
            }
            2 -> {
                addDueDurationToTodayDate(7)
            }
            3 -> {
                addDueDurationToTodayDate(14)

            }
            4 -> {
                addDueDurationToTodayDate(30)

            }
            5 -> {
                addDueDurationToTodayDate(30 * 2)
            }
            6 -> {
                addDueDurationToTodayDate(30 * 3)
            }

        }

        val name = binding?.edtNama?.text.toString()
        val keperluan = binding?.edtKeperluan?.text.toString()
        val formattedTotal = total?.let { ArtaLeleHelper.convertStringToNumberOnly(it) }

        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT)
                .show()
        }

        try {
            viewModel?.insertDebt(
                DebtEntity(
                    name = name,
                    amount = formattedTotal ?: 0,
                    startDate = startDate,
                    dueDate = dueDate,
                    description = keperluan,
                    isBillable = isBillable,
                    isPaidOff = false
                )
            )
        } finally {
            view.findNavController().popBackStack()
        }
    }

    private fun addDueDurationToTodayDate(dayDuration: Int) {
        calendar.add(Calendar.DAY_OF_YEAR, dayDuration)
        dueDate = calendar.time
    }


    override fun onDestroy() {
        super.onDestroy()
        _bindingTambahHutangFragment = null
    }

}