package com.ibnu.artalele.ui.home.pemasukan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.ui.home.pemasukan.repo.PemasukanRepositoryImpl

class IncomeViewModel(private val repository: PemasukanRepositoryImpl) : ViewModel() {

    fun getIncomeList(): LiveData<PagingData<IncomeEntity>> = repository.getListIncome()

}