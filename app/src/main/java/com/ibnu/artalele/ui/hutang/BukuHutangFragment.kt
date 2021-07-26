package com.ibnu.artalele.ui.hutang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibnu.artalele.databinding.BukuHutangFragmentBinding

class BukuHutangFragment : Fragment() {

    private lateinit var viewModel: BukuHutangViewModel

    private var _bindingBukuHutangFragment: BukuHutangFragmentBinding? = null
    private val binding get() = _bindingBukuHutangFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingBukuHutangFragment = BukuHutangFragmentBinding.inflate(inflater, container, false)
        return _bindingBukuHutangFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingBukuHutangFragment = null
    }

}