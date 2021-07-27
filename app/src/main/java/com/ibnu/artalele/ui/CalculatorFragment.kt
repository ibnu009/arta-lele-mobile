package com.ibnu.artalele.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.FragmentCalculatorBinding
import com.ibnu.artalele.databinding.HomeFragmentBinding
import com.ibnu.artalele.utils.ConstValue.DEBT_REQUEST_KEY

class CalculatorFragment : Fragment() {

    private var total: String = "0"
    private var isNew: Boolean = true
    private var isComma: Boolean = false

    private var _calculatorFragment: FragmentCalculatorBinding? = null
    private val binding get() = _calculatorFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _calculatorFragment = FragmentCalculatorBinding.inflate(inflater, container, false)
        return _calculatorFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnSave?.setOnClickListener {
            setFragmentResult(DEBT_REQUEST_KEY, bundleOf("result" to total))
            it.findNavController().popBackStack()
        }

        numberClickEvent()
    }

    private fun numberClickEvent() {
        if (isNew) {
            total = ""
            binding?.tvResult?.text = total
        }
        isNew = false

        val select = Button(requireContext())
        
        when (select.id) {
            binding?.btnZero?.id -> {
                appendNumberAndDisplay("0")
            }
            binding?.btnOne?.id -> {
                appendNumberAndDisplay("1")
            }
            binding?.btnTwo?.id -> {
                appendNumberAndDisplay("2")
            }
            binding?.btnThree?.id -> {
                appendNumberAndDisplay("3")
            }
            binding?.btnFour?.id -> {
                appendNumberAndDisplay("4")
            }
            binding?.btnFive?.id -> {
                appendNumberAndDisplay("5")
            }
            binding?.btnSix?.id -> {
                appendNumberAndDisplay("6")
            }
            binding?.btnSeven?.id -> {
                appendNumberAndDisplay("7")
            }
            binding?.btnEight?.id -> {
                appendNumberAndDisplay("8")
            }
            binding?.btnNine?.id -> {
                appendNumberAndDisplay("9")
            }
            binding?.btnTripleZeros?.id -> {
                appendNumberAndDisplay("000")
            }
            binding?.btnComma?.id -> {
                isComma = true
                appendNumberAndDisplay(".")
            }


        }
    }

    private fun appendNumberAndDisplay(value: String) {
        total += value
        binding?.tvResult?.text = total
    }


}