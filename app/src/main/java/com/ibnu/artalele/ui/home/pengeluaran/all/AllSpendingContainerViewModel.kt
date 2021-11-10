package com.ibnu.artalele.ui.home.pengeluaran.all

import androidx.lifecycle.ViewModel
import com.ibnu.artalele.ui.home.pengeluaran.repo.SpendingRepositoryImpl

class AllSpendingContainerViewModel(private val repo: SpendingRepositoryImpl) : ViewModel() {

    fun getSpendingTransactions(month: String, year: Int) = repo.getListSpending(month, year)

}