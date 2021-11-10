package com.ibnu.artalele.ui.home.tambah

import org.junit.Test

import org.junit.Assert.*

class TransactionRepositoryImplTest {

    @Test
    fun getAnnualIncomeTotal() {
        val expectedResult = listOf(100F, 200F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F)
        val result = ArrayList<Float>()
        val testIncomes = listOf(100, 200)
        for (i in 0..11) {
            var testTotal = 0F
            if (i < testIncomes.size) {
                testTotal = testIncomes[i].toFloat()
            }
            result.add(testTotal)
        }
        assertEquals(expectedResult, result)
    }

    @Test
    fun getAnnualSpendingTotal() {
    }
}