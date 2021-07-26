package com.ibnu.artalele.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibnu.artalele.ui.hutang.repo.TambahBukuHutangRepository
import com.ibnu.artalele.ui.hutang.tambah.TambahHutangViewModel

class ViewModelFactory(
    private val tambahBukuHutangRepository: TambahBukuHutangRepository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            (instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideTambahBukuHutangRepository(context)
                )
            })
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TambahHutangViewModel::class.java) -> {
                TambahHutangViewModel(tambahBukuHutangRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}