package com.ibnu.artalele.ui.hutang.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.DetailHutangFragmentBinding
import com.ibnu.artalele.databinding.ToolbarEditBinding
import com.ibnu.artalele.di.ViewModelFactory

class DetailHutangFragment : Fragment() {

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(DetailHutangViewModel::class.java)
    }
    private var _bindingDetailHutangFragment: DetailHutangFragmentBinding? = null
    private val binding get() = _bindingDetailHutangFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingDetailHutangFragment =
            DetailHutangFragmentBinding.inflate(inflater, container, false)
        return _bindingDetailHutangFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs = arguments?.let { DetailHutangFragmentArgs.fromBundle(it) }
        val id = safeArgs?.passHutangId
        val toolbar = binding?.toolbar

        initiateData(id ?: -1)
        deleteDebt(id ?: -1, toolbar)
    }

    private fun initiateData(id: Int) {
        viewModel?.getDebtId(id)?.observe(viewLifecycleOwner, Observer { debt ->
            binding?.tvDate?.text = debt.startDate
            binding?.tvTotal?.text = debt.amount
            binding?.tvKeperluan?.text = debt.description
            binding?.tvName?.text = debt.name
        })
    }

    private fun deleteDebt(id: Int, toolbar: ToolbarEditBinding?) {
        toolbar?.btnDeleteToolbar?.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Hapus Hutang")
                setMessage("Apakah Anda yakin untuk menghapus hutang ini?")
                setNegativeButton("Tidak") { p0, _ ->
                    p0.dismiss()
                }
                setPositiveButton("IYA") { _, _ ->
                    viewModel?.deleteDebt(id)
                }
            }.create().show()
        }
    }

}