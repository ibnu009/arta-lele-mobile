package com.ibnu.artalele.ui.home.pengeluaran.repo

import androidx.paging.*
import com.ibnu.artalele.data.dao.SpendingDao
import com.ibnu.artalele.utils.SearchUtils

class SpendingSource(private val dao: SpendingDao) {

    fun get15NewestSpending(day: Int, month: String) = Pager(
        config = PagingConfig(15)
    ) {
        dao.get15NewestSpending(day, month)
    }.liveData

    fun getAllSpending(month: String, year: Int) = Pager(
        config = PagingConfig(15)
    ) {
        val query = SearchUtils.getAllSpendingTransaction(month, year)
        dao.getAllSpending(query)
    }.liveData



    suspend fun getSpendingById(id: Int) = dao.getSpendingById(id)

    suspend fun deleteSpending(id: Int) = dao.deleteSpending(id)

    suspend fun getThisMonthSpendingTotal(month: String) = dao.getThisMonthSpendingTotal(month)
}