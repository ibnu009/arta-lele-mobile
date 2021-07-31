package com.ibnu.artalele.ui.home.tambah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.TambahTransaksiFragmentBinding
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.ConstValue
import com.ibnu.artalele.utils.ConstValue.CALCULATOR_INCOME
import com.ibnu.artalele.utils.ConstValue.CALCULATOR_SPENDING
import com.ibnu.artalele.utils.ConstValue.INCOME
import com.ibnu.artalele.utils.ConstValue.INCOME_REQUEST_KEY
import com.ibnu.artalele.utils.ConstValue.INCOME_RESULT_KEY
import com.ibnu.artalele.utils.ConstValue.NOTE_REQUEST_KEY
import com.ibnu.artalele.utils.ConstValue.NOTE_RESULT_KEY
import com.ibnu.artalele.utils.ConstValue.SPENDING_REQUEST_KEY
import com.ibnu.artalele.utils.ConstValue.SPENDING_RESULT_KEY

class TambahTransaksiFragment : Fragment() {

    private var _bindingTambahTraksaksiFragment: TambahTransaksiFragmentBinding? = null
    private val binding get() = _bindingTambahTraksaksiFragment

    private var category = ""
    private var weight: Int = 0
    private var grandTotal: Int = 0
    private var total: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingTambahTraksaksiFragment =
            TambahTransaksiFragmentBinding.inflate(inflater, container, false)
        return _bindingTambahTraksaksiFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateButtons()
    }

    private fun initiateButtons() {
        binding?.selectTotalOrWeight?.setOnClickListener {
            if (category.isEmpty()) {
                Toast.makeText(requireContext(), "Pilih Category terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }

            val action: NavDirections = if (category == INCOME) {
                TambahTransaksiFragmentDirections.actionTambahTransaksiFragmentToCalculatorFragment(
                    CALCULATOR_INCOME)
            } else {
                TambahTransaksiFragmentDirections.actionTambahTransaksiFragmentToCalculatorFragment(
                    CALCULATOR_SPENDING)
            }

            it.findNavController().navigate(action)
        }

        binding?.selectCategory?.setOnClickListener {

        }

        binding?.selectDate?.setOnClickListener {

        }

        binding?.addNote?.setOnClickListener {
            it.findNavController().navigate(R.id.action_tambahTransaksiFragment_to_deskriptionFragment)
        }
    }

    private fun setFragmentResults() {
        setFragmentResultListener(SPENDING_REQUEST_KEY) { _, bundle ->
            val rawTotal = ArtaLeleHelper.addRupiahToThousandAmountFromString(bundle.getString(SPENDING_RESULT_KEY) ?: "0")
            binding?.tvTotalOrWeight?.text = rawTotal
        }

        setFragmentResultListener(INCOME_REQUEST_KEY) { _, bundle ->
            val rawTotal = ArtaLeleHelper.addKgToAmountFromString(bundle.getString(INCOME_RESULT_KEY) ?: "0")
            binding?.tvTotalOrWeight?.text = rawTotal
        }

        setFragmentResultListener(NOTE_REQUEST_KEY) { _, bundle ->
            val rawTotal = ArtaLeleHelper.addKgToAmountFromString(bundle.getString(NOTE_RESULT_KEY) ?: "-")
            binding?.tvDesc?.text = rawTotal
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingTambahTraksaksiFragment = null
    }
}