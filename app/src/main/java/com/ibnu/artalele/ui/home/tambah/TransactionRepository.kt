package com.ibnu.artalele.ui.home.tambah

import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.data.entities.SpendingEntity

interface TransactionRepository {

    suspend fun getCategoryById(id: Int): CategoryEntity

    suspend fun insertIncome(incomeEntity: IncomeEntity)

    suspend fun insertSpending(spendingEntity: SpendingEntity)

    suspend fun updateIncome(incomeEntity: IncomeEntity)

    suspend fun updateSpending(spendingEntity: SpendingEntity)

    suspend fun getAnnualIncomeChartData(year: Int): List<Float>

    suspend fun getAnnualSpendingChartData(year: Int): List<Float>

    suspend fun getAnnualIncomeTotal(year: Int): Int

    suspend fun getAnnualSpendingTotal(year: Int): Int

}