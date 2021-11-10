package com.ibnu.artalele.ui.home.report

import androidx.lifecycle.*
import com.github.mikephil.charting.data.BarEntry
import com.ibnu.artalele.ui.home.tambah.TransactionRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class ReportViewModel(private val repository: TransactionRepositoryImpl) : ViewModel() {

    fun getIncomeBarChartData(year: Int): LiveData<List<Float>> {
        val result = MutableLiveData<List<Float>>()
        viewModelScope.launch {
            result.postValue(repository.getAnnualIncomeChartData(year))
        }
        return result
    }

    fun getSpendingBarChartData(year: Int): LiveData<List<Float>> {
        val result = MutableLiveData<List<Float>>()
        viewModelScope.launch {
            result.postValue(repository.getAnnualSpendingChartData(year))

        }
        return result
    }

    fun getIncomeAnnualTotal(year: Int): LiveData<Int> {
        val incomeTotal = MutableLiveData<Int>()
        viewModelScope.launch {
            incomeTotal.postValue(repository.getAnnualIncomeTotal(year))
        }
        return incomeTotal
    }

    fun getSpendingAnnualTotal(year: Int): LiveData<Int> {
        val spendingTotal = MutableLiveData<Int>()
        viewModelScope.launch {
            spendingTotal.postValue(repository.getAnnualSpendingTotal(year))
        }
        return spendingTotal
    }

}