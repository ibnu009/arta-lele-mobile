package com.ibnu.artalele.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ArtaLeleHelper {

    companion object {
        fun convertStringToNumberOnly(string: String): Int {
            val rawNumber = string.replace("\\D+", "")
            return Integer.parseInt(rawNumber)
        }

        fun addRupiahToAmount(amount: Int): String {
            val formattedAmount = NumberFormat.getIntegerInstance().format(amount)
            return "Rp $formattedAmount"
        }

        fun getTodayDate(): String {
            val date = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)

            return dateFormat.format(date)
        }
    }

}