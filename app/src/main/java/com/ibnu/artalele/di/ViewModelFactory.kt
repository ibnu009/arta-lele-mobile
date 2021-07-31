package com.ibnu.artalele.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibnu.artalele.ui.extra.category.IncomeCategoryViewModel
import com.ibnu.artalele.ui.extra.category.SpendingCategoryViewModel
import com.ibnu.artalele.ui.extra.category.repo.CategoryRepositoryImpl
import com.ibnu.artalele.ui.home.pemasukan.IncomeViewModel
import com.ibnu.artalele.ui.home.pemasukan.repo.PemasukanRepositoryImpl
import com.ibnu.artalele.ui.hutang.BukuHutangViewModel
import com.ibnu.artalele.ui.hutang.detail.DetailHutangViewModel
import com.ibnu.artalele.ui.hutang.repo.BukuHutangRepositoryImpl
import com.ibnu.artalele.ui.hutang.repo.TambahBukuHutangRepository
import com.ibnu.artalele.ui.hutang.tambah.TambahHutangViewModel

class ViewModelFactory(
    private val tambahBukuHutangRepository: TambahBukuHutangRepository,
    private val bukuHutangRepository: BukuHutangRepositoryImpl,
    private val pemasukanRepositoryImpl: PemasukanRepositoryImpl,
    private val categoryRepositoryImpl: CategoryRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            (instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideTambahBukuHutangRepository(context),
                    Injection.provideBukuHutangRepository(context),
                    Injection.provideIncomeRepository(context),
                    Injection.provideCategoryRepository(context)
                )
            })
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TambahHutangViewModel::class.java) -> {
                TambahHutangViewModel(tambahBukuHutangRepository) as T
            }
            modelClass.isAssignableFrom(BukuHutangViewModel::class.java) -> {
                BukuHutangViewModel(bukuHutangRepository) as T
            }
            modelClass.isAssignableFrom(DetailHutangViewModel::class.java) -> {
                DetailHutangViewModel(bukuHutangRepository) as T
            }
            modelClass.isAssignableFrom(IncomeViewModel::class.java) -> {
                IncomeViewModel(pemasukanRepositoryImpl) as T
            }
            modelClass.isAssignableFrom(IncomeCategoryViewModel::class.java) -> {
                IncomeCategoryViewModel(categoryRepositoryImpl) as T
            }
            modelClass.isAssignableFrom(SpendingCategoryViewModel::class.java) -> {
                SpendingCategoryViewModel(categoryRepositoryImpl) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}