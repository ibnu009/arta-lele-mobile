package com.ibnu.artalele.ui.hutang

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.DebtEntity
import com.ibnu.artalele.ui.hutang.repo.BukuHutangRepositoryImpl

class BukuHutangViewModel(private val repository: BukuHutangRepositoryImpl) : ViewModel() {

    fun getDebts(type: String): LiveData<PagingData<DebtEntity>> = repository.getDebts(type)

    fun getSearchDebt(keyword: String): LiveData<PagingData<DebtEntity>> =
        repository.getDebtsSearch(keyword)

}