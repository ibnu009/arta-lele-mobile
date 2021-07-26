package com.ibnu.artalele.utils

import android.content.Context
import android.content.SharedPreferences
import com.ibnu.artalele.utils.ConstValue.KEY_BASE_PRICE
import com.ibnu.artalele.utils.ConstValue.PREFS_NAME

class SharedPreferencesManager(context: Context) {
    private var prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    fun setBasePrice(price: Int) {
        editor.putInt(KEY_BASE_PRICE, price)
        editor.apply()
    }

    val getPrice = prefs.getInt(KEY_BASE_PRICE, 0)
}