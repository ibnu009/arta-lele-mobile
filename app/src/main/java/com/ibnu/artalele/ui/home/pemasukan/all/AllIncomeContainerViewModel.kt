package com.ibnu.artalele.ui.home.pemasukan.all

import androidx.lifecycle.ViewModel
import com.ibnu.artalele.ui.home.pemasukan.repo.PemasukanRepositoryImpl

class AllIncomeContainerViewModel(private val repo: PemasukanRepositoryImpl) : ViewModel() {

    fun getIncomes(month: String, year: Int) = repo.getListIncome(month, year)

}