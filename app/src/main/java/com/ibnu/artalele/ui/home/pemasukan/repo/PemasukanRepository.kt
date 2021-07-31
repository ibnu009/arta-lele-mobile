package com.ibnu.artalele.ui.home.pemasukan.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.IncomeEntity

interface PemasukanRepository {
    fun getListIncome(): LiveData<PagingData<IncomeEntity>>
}