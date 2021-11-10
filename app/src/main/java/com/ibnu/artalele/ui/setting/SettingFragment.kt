package com.ibnu.artalele.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var _bindingSettingFragment: FragmentSettingBinding? = null
    private val binding get() = _bindingSettingFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingSettingFragment = FragmentSettingBinding.inflate(inflater,container, false)
        return _bindingSettingFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.tvToolbarTitle?.text = "Pengaturan"

        binding?.toolbar?.imgBack?.visibility = View.GONE

        binding?.selectBasePrice?.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment2_to_basePriceFragment)
        }
    }
}