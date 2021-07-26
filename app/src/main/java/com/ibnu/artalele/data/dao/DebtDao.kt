package com.ibnu.artalele.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.ibnu.artalele.data.entities.DebtEntity

@Dao
interface DebtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebt(debt: DebtEntity)

    @Query("SELECT * FROM debt ORDER BY start_date DESC")
    fun getNewestDebt(): PagingSource<Int, DebtEntity>

    @Query("SELECT * FROM debt ORDER BY start_date ASC")
    fun getOldestDebt(): PagingSource<Int, DebtEntity>

    @Query ("SELECT * FROM debt WHERE id = :id")
    fun getDebtById(id: Int): DebtEntity

    @Update
    fun updateDebt(debt: DebtEntity)

    @Delete
    fun deleteDebt(debt: DebtEntity)

}