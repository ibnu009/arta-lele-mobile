package com.ibnu.artalele.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SearchUtils {

    fun getSearchDebtResult(keyword: String): SimpleSQLiteQuery {
        val simpleSQLiteQuery = StringBuilder().append("SELECT * FROM debt ")
        simpleSQLiteQuery.append("JOIN debt_fts ON debt.name = debt_fts.name ")
        simpleSQLiteQuery.append("WHERE debt_fts MATCH $keyword")

        return SimpleSQLiteQuery(simpleSQLiteQuery.toString())
    }

    fun getAllSpendingTransaction(month: String, year: Int): SimpleSQLiteQuery {
        val simpleSQLiteQuery = StringBuilder().append("SELECT * FROM spending ")
        simpleSQLiteQuery.append("WHERE month='$month' AND year=$year ORDER BY day DESC")
        return SimpleSQLiteQuery(simpleSQLiteQuery.toString())
    }

    fun getAllIncomeTransaction(month: String, year: Int): SimpleSQLiteQuery {
        val simpleSQLiteQuery = StringBuilder().append("SELECT * FROM income ")
        simpleSQLiteQuery.append("WHERE month='$month' AND year=$year ORDER BY day DESC")
        return SimpleSQLiteQuery(simpleSQLiteQuery.toString())
    }

}