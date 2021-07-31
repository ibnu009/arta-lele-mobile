package com.ibnu.artalele.ui.home.pemasukan.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.ui.home.pemasukan.source.IncomeSource

class PemasukanRepositoryImpl(private val incomeSource: IncomeSource) : PemasukanRepository {
    override fun getListIncome(): LiveData<PagingData<IncomeEntity>> =
        incomeSource.getDebts()

}