package com.ibnu.artalele.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.ibnu.artalele.data.entities.IncomeEntity

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncome(income: IncomeEntity)

    @Query("SELECT * FROM income ORDER BY id ASC")
    fun getAllIncome(): PagingSource<Int, IncomeEntity>

    @Query("SELECT * FROM income WHERE id= :id")
    fun getIncomeById(id: Int): PagingSource<Int, IncomeEntity>

    @Update
    fun updateIncome(income: IncomeEntity)

    @Delete
    fun deleteIncome(income: IncomeEntity)

}