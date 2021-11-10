package com.ibnu.artalele.ui.home.pemasukan.all

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnu.artalele.databinding.AllIncomeContainerFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.ui.home.HomeFragmentDirections
import com.ibnu.artalele.ui.home.pemasukan.adapter.IncomeAdapter
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler

class AllIncomeContainerFragment(private val month: String, private val year: Int) : Fragment() {

    private lateinit var adapter: IncomeAdapter

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(AllIncomeContainerViewModel::class.java)
    }

    private var _bindingAllIncomeFragment: AllIncomeContainerFragmentBinding? = null
    private val binding get() = _bindingAllIncomeFragment

    private val onClick = object : RecyclerviewItemClickHandler {
        override fun onClickItem(id: Int) {
            val action =
                AllIncomeFragmentDirections.actionAllIncomeFragmentToDetailIncomeFragment(id)
            view?.findNavController()?.navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingAllIncomeFragment =
            AllIncomeContainerFragmentBinding.inflate(inflater, container, false)
        return _bindingAllIncomeFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateRecyclerview()
        initiateData()
    }

    private fun initiateRecyclerview() {
        adapter = IncomeAdapter(onClick)
        adapter.addLoadStateListener { loadState ->
            if (loadState.append.endOfPaginationReached) {
                if (adapter.itemCount < 1) {
                    showEmptyItemView(true)
                } else {
                    showEmptyItemView(false)
                }
            }
        }
        binding?.rvAllIncome?.adapter = adapter
        binding?.rvAllIncome?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initiateData() {
        viewModel?.getIncomes(month, year)?.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
    }

    private fun showEmptyItemView(isEmpty: Boolean) {
        if (isEmpty) {
            binding?.tvTestingBoss?.visibility = View.VISIBLE
            binding?.rvAllIncome?.visibility = View.GONE
            binding?.tvTestingBoss?.text = "Transaksi Bulan $month Kosong!"
        } else {
            binding?.tvTestingBoss?.visibility = View.GONE
            binding?.rvAllIncome?.visibility = View.VISIBLE
        }

    }


}