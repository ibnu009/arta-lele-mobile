package com.ibnu.artalele.ui.home

import android.content.Context
import android.os.Bundle
import android.os.UserManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private var _bindingHomeFragment: HomeFragmentBinding? = null
    private val binding get() = _bindingHomeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingHomeFragment = HomeFragmentBinding.inflate(inflater, container, false)
        return _bindingHomeFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateViewPager(requireContext())

    }

    private fun initiateViewPager(context: Context) {
        val homeViewpager = HomeViewPagerAdapter(requireActivity().supportFragmentManager, context)
        binding?.transacationViewpager?.adapter = homeViewpager
        binding?.homeTablayout?.setupWithViewPager(binding?.transacationViewpager)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _bindingHomeFragment = null
    }
}