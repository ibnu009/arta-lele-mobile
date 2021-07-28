package com.ibnu.artalele.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object TypeUtils{

    const val NEW_DEBT = "mew debt"
    const val OLD_DEBT = "old debt"

    fun getDebtByType(filterType: String): SimpleSQLiteQuery {
        val simpleSQLiteQuery = StringBuilder().append("SELECT * FROM debt ")
        when (filterType) {
            NEW_DEBT -> {
                simpleSQLiteQuery.append("ORDER BY start_date DESC")
            }
            OLD_DEBT -> {
                simpleSQLiteQuery.append("ORDER BY start_date ASC")
            }
        }
        return SimpleSQLiteQuery(simpleSQLiteQuery.toString())
    }

}
