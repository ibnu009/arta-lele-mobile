package com.ibnu.artalele.ui.home.pengeluaran

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibnu.artalele.R

class SpendingFragment : Fragment() {

    companion object {
        fun newInstance() = SpendingFragment()
    }

    private lateinit var viewModel: SpendingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.spending_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpendingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}