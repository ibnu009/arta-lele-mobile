package com.ibnu.artalele.ui.home.pemasukan

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibnu.artalele.R

class IncomeFragment : Fragment() {

    companion object {
        fun newInstance() = IncomeFragment()
    }

    private lateinit var viewModel: IncomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.income_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}