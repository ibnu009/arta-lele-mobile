package com.ibnu.artalele.ui.home.pemasukan.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ibnu.artalele.data.dao.IncomeDao

class IncomeSource(private val incomeDao: IncomeDao) {

    fun getDebts() = Pager(
        config = PagingConfig(10)
    ){
        incomeDao.getAllIncome()
    }.liveData


}