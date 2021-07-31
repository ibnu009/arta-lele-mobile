package com.ibnu.artalele.ui.home.pemasukan

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnu.artalele.databinding.IncomeFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.ui.home.pemasukan.adapter.IncomeAdapter
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler

class IncomeFragment : Fragment() {

    private lateinit var adapter: IncomeAdapter

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(IncomeViewModel::class.java)
    }

    private var _bindingIncomeFragment: IncomeFragmentBinding? = null
    private val binding get() = _bindingIncomeFragment

    private val onClick = object: RecyclerviewItemClickHandler {
        override fun onClickItem(id: Int) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingIncomeFragment = IncomeFragmentBinding.inflate(inflater, container, false)
        return _bindingIncomeFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateRecyclerview()
        initiateData()
    }


    private fun initiateRecyclerview() {
        adapter = IncomeAdapter(onClick)
        binding?.rvParentIncome?.adapter = adapter
        binding?.rvParentIncome?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initiateData() {
        viewModel?.getIncomeList()?.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle,it)
        })
    }


}