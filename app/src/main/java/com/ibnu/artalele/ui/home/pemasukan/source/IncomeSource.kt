package com.ibnu.artalele.ui.home.pemasukan.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ibnu.artalele.data.dao.IncomeDao
import com.ibnu.artalele.utils.SearchUtils

class IncomeSource(private val incomeDao: IncomeDao) {

    fun getIncomes(month: String, year: Int) = Pager(
        config = PagingConfig(10)
    ) {
        val query = SearchUtils.getAllIncomeTransaction(month, year)
        incomeDao.getAllIncome(query)
    }.liveData

    fun get15NewestIncomes(day: Int, month: String) = Pager(
        config = PagingConfig(15)
    ) {
        incomeDao.getOnly15NewestIncomes(day, month)
    }.liveData

    suspend fun getDebtById(id: Int) = incomeDao.getIncomeById(id)

    suspend fun getThisMonthIncomeTotal(month: String, year: Int) =
        incomeDao.getIncomeTotalByMonthAndYear(month, year)

    suspend fun deleteIncome(id: Int) = incomeDao.deleteIncome(id)


}