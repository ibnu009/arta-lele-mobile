package com.ibnu.artalele.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class ArtaLeleHelper {

    companion object {
        fun convertStringToNumberOnly(string: String): Int {
            val rawNumber = string.filter { it.isDigit() }
            return Integer.parseInt(rawNumber)
        }

        fun addRupiahToAmount(amount: Int): String {
            val formattedAmount = NumberFormat.getIntegerInstance().format(amount)
            return "Rp $formattedAmount"
        }

        fun addRupiahToThousandAmountFromString(amount: String): String {
            var rawAmount = ""
            rawAmount = if (amount.contains(".")) {
                amount.substring(0, amount.indexOf("."))
            } else {
                amount
            }
            val formattedAmount = NumberFormat.getIntegerInstance().format(rawAmount.toInt())
            return "Rp $formattedAmount"
        }

        fun getTodayDate(): String {
            val date = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)

            return dateFormat.format(date)
        }
    }

}