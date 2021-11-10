package com.ibnu.artalele.ui.home.pengeluaran.all

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.ibnu.artalele.databinding.AllSpendingFragmentBinding
import com.ibnu.artalele.ui.home.TransactionViewPagerAdapter
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.ConstValue
import com.ibnu.artalele.utils.ConstValue.SPENDING
import com.ibnu.artalele.utils.ConstValue.titles
import java.util.*

class AllSpendingFragment : Fragment() {

    private val calendar: Calendar = Calendar.getInstance()

    private var _bindingAllSpendingFragment: AllSpendingFragmentBinding? = null
    private val binding get() = _bindingAllSpendingFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingAllSpendingFragment = AllSpendingFragmentBinding.inflate(inflater, container, false)
        return _bindingAllSpendingFragment?.root
    }

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { p0, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.tvToolbarTitle?.text = "Catatan Pengeluaran"
        binding?.toolbar?.imgCalendar?.setOnClickListener {
            val dialog = DatePickerDialog(
                requireContext(),
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),

                )
            dialog.datePicker.touchables[0].performClick()
            dialog.datePicker.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ) { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                showYear(calendar)
                dialog.dismiss()
            }
            dialog.show()
        }
        binding?.toolbar?.imgBack?.setOnClickListener {
            it.findNavController().popBackStack()
        }

        val currentYear = ArtaLeleHelper.getCurrentYear()
        initiateViewPager(currentYear)
    }

    private fun initiateViewPager(year: Int) {
        val currentMonth = ArtaLeleHelper.getCurrentMonth()
        val currentYear = ArtaLeleHelper.getCurrentYear()

        val transactionViewPager =
            TransactionViewPagerAdapter(requireActivity(), SPENDING, titles, year)
        binding?.transacationViewpager?.adapter = transactionViewPager

        val viewPager = binding?.transacationViewpager!!
        if (currentYear == year) {
            viewPager.currentItem = titles.indexOf(currentMonth)
        } else {
            viewPager.currentItem = 0
        }

        TabLayoutMediator(
            binding?.allSpendingTablayout!!, viewPager
        ) { tab, position ->
            tab.text = "${titles[position]} - $year"
        }.attach()
    }

    private fun showYear(calendar: Calendar) {
        val rawYear = ArtaLeleHelper.formatSelectedYear(calendar)
        val year = Integer.parseInt(rawYear)
        initiateViewPager(year)
    }
}