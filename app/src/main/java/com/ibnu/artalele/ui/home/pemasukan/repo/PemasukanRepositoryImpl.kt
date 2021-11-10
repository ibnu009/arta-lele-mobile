package com.ibnu.artalele.ui.home.pemasukan.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.ui.home.pemasukan.source.IncomeSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PemasukanRepositoryImpl(private val incomeSource: IncomeSource) : PemasukanRepository {

    override fun getListIncome(month: String, year: Int): LiveData<PagingData<IncomeEntity>> =
        incomeSource.getIncomes(month, year)

    override fun get15Incomes(day: Int, month: String): LiveData<PagingData<IncomeEntity>> =
        incomeSource.get15NewestIncomes(day, month)

    override suspend fun getThisMonthIncomeTotal(month: String, year: Int): Int {
        var total = 0
        withContext(Dispatchers.Default) {
            val listIncome = incomeSource.getThisMonthIncomeTotal(month, year)
            for (i in listIncome) {
                total += i
            }
        }
        return total
    }

    override suspend fun getIncomeById(id: Int): IncomeEntity = incomeSource.getDebtById(id)

    override suspend fun deleteIncome(id: Int) = incomeSource.deleteIncome(id)

}