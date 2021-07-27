package com.ibnu.artalele.ui.hutang.tambah

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ibnu.artalele.R
import com.ibnu.artalele.data.entities.DebtEntity
import com.ibnu.artalele.databinding.TambahHutangFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.utils.ArtaLeleHelper

class TambahHutangFragment : Fragment() {

    private var total = 0
    private var date = ""

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

        initiateView()
        binding?.btnSave?.setOnClickListener {
            saveDebt()
        }

        binding?.selectTotal?.setOnClickListener {
            view.findNavController().navigate(R.id.action_tambahHutangFragment_to_calculatorFragment)
        }

    }

    private fun initiateView() {
        date = ArtaLeleHelper.getTodayDate()
        binding?.tvDate?.text = date
    }

    private fun saveDebt() {
        val name = binding?.edtNama?.text.toString()
        val keperluan = binding?.edtKeperluan?.text.toString()

        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT)
                .show()
        }
        viewModel?.insertDebt(
            DebtEntity(
                name = name,
                amount = total.toString(),
                startDate = date,
                dueDate = date,
                description = keperluan
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _bindingTambahHutangFragment = null
    }

}