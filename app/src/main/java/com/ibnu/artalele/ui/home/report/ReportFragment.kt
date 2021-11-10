package com.ibnu.artalele.ui.home.report

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.ibnu.artalele.databinding.ReportFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.utils.*
import timber.log.Timber

class ReportFragment : Fragment() {

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(ReportViewModel::class.java)
    }

    private var _bindingReportFragment: ReportFragmentBinding? = null
    private val binding get() = _bindingReportFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingReportFragment = ReportFragmentBinding.inflate(inflater, container, false)
        return _bindingReportFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentYear = ArtaLeleHelper.getCurrentYear()
        initiateData(currentYear)
    }

    private fun initiateData(year: Int) {

        viewModel?.getIncomeAnnualTotal(year)?.observe(viewLifecycleOwner, Observer { income ->
            binding?.tvIncome?.text = ArtaLeleHelper.addRupiahToAmount(income)
        })

        viewModel?.getSpendingAnnualTotal(year)?.observe(viewLifecycleOwner, Observer { spending ->
            binding?.tvSpending?.text = ArtaLeleHelper.addRupiahToAmount(spending)
        })

        val emptySpending = listOf(
            BarEntry(0f, 100000f),
            BarEntry(1f, 100000f),
            BarEntry(2f, 1000000f),
            BarEntry(3f, 1000000f),
            BarEntry(4f, 1000000f),
            BarEntry(5f, 1000000f),
            BarEntry(6f, 1000000f),
            BarEntry(7f, 1000000f),
            BarEntry(8f, 0f),
            BarEntry(9f, 1000000f),
            BarEntry(10f, 0f),
            BarEntry(11f, 1000000f),
        )

        val listIncome = ArrayList<BarEntry>()
        val listSpending = ArrayList<BarEntry>()

        viewModel?.getIncomeBarChartData(year)?.observe(viewLifecycleOwner, Observer {income ->
            for (i in income.indices) {
                listIncome.add(BarEntry(i.toFloat(), income[i]))
            }
            viewModel?.getSpendingBarChartData(year)?.observe(viewLifecycleOwner, Observer {spending ->
                for (i in spending.indices) {
                    listSpending.add(BarEntry(i.toFloat(), spending[i]))
                    Timber.d("data adalah ${spending[i]} with index $i")
                    initiateBarChart(listIncome, listSpending)
                }
            })
        })
    }

    private fun initiateBarChart(
        incomeData: List<BarEntry>?,
        spendingData: List<BarEntry>?
    ) {

        val barChart = binding?.transactionChart
        barChart?.description?.isEnabled = false
        barChart?.setFitBars(true)
        barChart?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
        barChart?.xAxis?.granularity = 1f
        barChart?.xAxis?.setCenterAxisLabels(true)
        barChart?.xAxis?.setDrawGridLines(true)
        barChart?.xAxis?.spaceMin = 0f

        val legend = barChart?.legend
        legend?.isEnabled = true
        legend?.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend?.orientation = Legend.LegendOrientation.HORIZONTAL
        legend?.setDrawInside(true)

        val incomeLine = BarDataSet(incomeData, "Pemasukan")
        incomeLine.color = Color.GREEN

        val spendingLine = BarDataSet(spendingData, "Pengeluaran")
        spendingLine.color = Color.RED

        val date = ArrayList<String>();
        date.add("Jan")
        date.add("Feb")
        date.add("Mar")
        date.add("Apr")
        date.add("may")
        date.add("jun")
        date.add("jul")
        date.add("aug")
        date.add("sep")
        date.add("oct")
        date.add("nov")
        date.add("des")
        val month = AxisMonthFormatter(date.toArray(arrayOfNulls<String>(date.size)))
        val groupSpace = 0.06f
        val barSpace = 0.05f
        val barWidth = ((1f - groupSpace) / 2) - barSpace

        val groupBar = BarData(incomeLine, spendingLine)
        groupBar.barWidth = barWidth
        barChart?.data = groupBar

        groupBar.setValueFormatter(ArtaLeleLargeAmountFormatter())
        barChart?.axisLeft?.valueFormatter = ArtaLeleLargeAmountFormatter()
        barChart?.axisRight?.isEnabled = false
        barChart?.xAxis?.axisMaximum = date.size - 0.25f
        barChart?.setVisibleXRangeMaximum(4f)
        barChart?.defaultValueFormatter
        barChart?.isDragEnabled = true
        barChart?.groupBars(0f, groupSpace, barSpace)
        barChart?.animateXY(100, 500)
        barChart?.xAxis?.valueFormatter = month;
    }
}