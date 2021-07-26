package com.ibnu.artalele.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.ibnu.artalele.data.entities.SpendingEntity

@Dao
interface SpendingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpending(spending: SpendingEntity)

    @Query("SELECT * FROM spending ORDER BY id ASC")
    fun getAllSpending(): PagingSource<Int, SpendingEntity>

    @Delete
    fun deleteSpending(spending: SpendingEntity)
}