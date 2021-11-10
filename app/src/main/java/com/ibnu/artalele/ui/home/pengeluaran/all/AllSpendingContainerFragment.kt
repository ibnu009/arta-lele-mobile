package com.ibnu.artalele.ui.home.pengeluaran.all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnu.artalele.databinding.AllSpendingContainerFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.ui.home.HomeFragmentDirections
import com.ibnu.artalele.ui.home.pengeluaran.SpendingAdapter
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler

class AllSpendingContainerFragment(private val month: String, private val year: Int) : Fragment() {

    private lateinit var adapter: SpendingAdapter

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(AllSpendingContainerViewModel::class.java)
    }

    private var _bindingAllSpendingFragment: AllSpendingContainerFragmentBinding? = null
    private val binding get() = _bindingAllSpendingFragment

    private val onClick = object : RecyclerviewItemClickHandler {
        override fun onClickItem(id: Int) {
            val action = AllSpendingFragmentDirections.actionAllSpendingFragmentToDetailSpendingFragment(id)
            view?.findNavController()?.navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingAllSpendingFragment =
            AllSpendingContainerFragmentBinding.inflate(inflater, container, false)
        return _bindingAllSpendingFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateRecyclerview()
        initiateData()
    }

    private fun initiateRecyclerview() {
        adapter = SpendingAdapter(onClick)
        adapter.addLoadStateListener { loadState ->
            if (loadState.append.endOfPaginationReached) {
                if (adapter.itemCount < 1) {
                    showEmptyItemView(true)
                } else {
                    showEmptyItemView(false)
                }
            }
        }
        binding?.rvAllSpending?.adapter = adapter
        binding?.rvAllSpending?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initiateData() {
        viewModel?.getSpendingTransactions(month, year)?.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
    }

    private fun showEmptyItemView(isEmpty: Boolean) {
        if (isEmpty) {
            binding?.tvTestingBoss?.visibility = View.VISIBLE
            binding?.rvAllSpending?.visibility = View.GONE
            binding?.tvTestingBoss?.text = "Transaksi Bulan $month Kosong!"
        } else {
            binding?.tvTestingBoss?.visibility = View.GONE
            binding?.rvAllSpending?.visibility = View.VISIBLE
        }

    }


}