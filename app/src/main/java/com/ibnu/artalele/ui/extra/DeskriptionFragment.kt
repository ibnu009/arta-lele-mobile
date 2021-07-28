package com.ibnu.artalele.ui.extra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.FragmentDeskriptionBinding

class DeskriptionFragment : Fragment() {

    private var _bindingFragmentDeskription: FragmentDeskriptionBinding? = null
    private val binding get() = _bindingFragmentDeskription

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingFragmentDeskription = FragmentDeskriptionBinding.inflate(inflater, container, false)
        return _bindingFragmentDeskription?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun initiateToolbar() {
        binding?.toolbarDescription?.tvToolbarTitle?.text = ""
    }

}