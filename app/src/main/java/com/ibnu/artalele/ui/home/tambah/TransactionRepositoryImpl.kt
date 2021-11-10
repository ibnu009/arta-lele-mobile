package com.ibnu.artalele.ui.home.tambah

import com.ibnu.artalele.data.dao.CategoryDao
import com.ibnu.artalele.data.dao.IncomeDao
import com.ibnu.artalele.data.dao.SpendingDao
import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.data.entities.SpendingEntity
import com.ibnu.artalele.utils.ConstValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class TransactionRepositoryImpl(
    private val categoryDao: CategoryDao,
    private val incomeDao: IncomeDao,
    private val spendingDao: SpendingDao
) : TransactionRepository {

    override suspend fun getCategoryById(id: Int): CategoryEntity =
        categoryDao.getSingleCategory(id)

    override suspend fun insertIncome(incomeEntity: IncomeEntity) {
        withContext(Dispatchers.IO) {
            incomeDao.insertIncome(incomeEntity)
        }
    }

    override suspend fun insertSpending(spendingEntity: SpendingEntity) {
        withContext(Dispatchers.IO) {
            spendingDao.insertSpending(spendingEntity)
        }
    }

    override suspend fun updateIncome(incomeEntity: IncomeEntity) {
        withContext(Dispatchers.IO){
            incomeDao.updateIncome(incomeEntity)
        }
    }

    override suspend fun updateSpending(spendingEntity: SpendingEntity) {
        withContext(Dispatchers.IO) {
            spendingDao.updateSpending(spendingEntity)
        }
    }

    override suspend fun getAnnualIncomeChartData(year: Int): List<Float> {
        val listFloat = ArrayList<Float>()
        for (i in ConstValue.titles.indices) {
            val data = getMonthlyTotal(ConstValue.titles[i], year)
            listFloat.add(data)
            Timber.d("Report - checking data $data with index $i")
        }
        return listFloat
    }

    private suspend fun getMonthlyTotal(month: String, year: Int): Float {
        var total = 0f
        withContext(Dispatchers.Default) {
            val listIncome = incomeDao.getIncomeTotalByMonthAndYear(month, year)
            for (i in listIncome) {
                total += i
            }
        }
        return total
    }

    override suspend fun getAnnualSpendingChartData(year: Int): List<Float> {
        val listSpending = ArrayList<Float>()
        for (i in ConstValue.titles.indices) {
            val data = getMonthlySpendingTotal(ConstValue.titles[i], year)
            listSpending.add(data)
        }
        return listSpending
    }

    private suspend fun getMonthlySpendingTotal(month: String, year: Int): Float {
        var total = 0f
        withContext(Dispatchers.Default) {
            val listSpending = spendingDao.getSpendingTotal(month, year)
            for (i in listSpending) {
                total += i
            }
        }
        return total
    }


    override suspend fun getAnnualIncomeTotal(year: Int): Int {
        var incomeTotal = 0
        withContext(Dispatchers.Default) {
            for (i in incomeDao.getAnnualIncomeTotal(year)) {
                incomeTotal += i
            }
        }
        return incomeTotal
    }

    override suspend fun getAnnualSpendingTotal(year: Int): Int {
        var spendingTotal = 0
        withContext(Dispatchers.Default) {
            for (i in spendingDao.getAnnualSpendingTotal(year)) {
                spendingTotal += i
            }
        }
        return spendingTotal
    }


}