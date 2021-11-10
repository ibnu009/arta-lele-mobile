package com.ibnu.artalele.utils

import com.github.mikephil.charting.formatter.ValueFormatter

class AxisMonthFormatter(private val mValues: Array<String>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return if (value >= 0) {
            if (mValues.size > value.toInt()) {
                mValues[value.toInt()]
            } else ""
        } else {
            ""
        }
    }
}