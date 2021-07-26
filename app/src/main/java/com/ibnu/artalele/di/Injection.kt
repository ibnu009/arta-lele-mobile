package com.ibnu.artalele.di

import android.app.Application
import android.content.Context
import com.ibnu.artalele.data.dao.DebtDao
import com.ibnu.artalele.data.dao.IncomeDao
import com.ibnu.artalele.data.dao.SpendingDao
import com.ibnu.artalele.data.database.ArtaDatabase
import com.ibnu.artalele.ui.hutang.repo.TambahBukuHutangRepository

object Injection {

    private fun provideDatabase(context: Context): ArtaDatabase = ArtaDatabase.getDatabase(context)

    private fun provideSpendingDao(context: Context): SpendingDao =
        provideDatabase(context).getSpendingDao()

    private fun provideIncome(context: Context): IncomeDao =
        provideDatabase(context).getIncomeDao()

    private fun provideDebtDao(context: Context): DebtDao =
        provideDatabase(context).getDebtDao()


    fun provideTambahBukuHutangRepository(context: Context): TambahBukuHutangRepository = TambahBukuHutangRepository(
        provideDebtDao(context))

}