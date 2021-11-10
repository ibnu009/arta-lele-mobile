package com.ibnu.artalele.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.ibnu.artalele.data.entities.SpendingEntity

@Dao
interface SpendingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpending(spending: SpendingEntity)

    @Update
    suspend fun updateSpending(spending: SpendingEntity)

    @Query("SELECT * FROM spending WHERE day= :currentDay AND month=:month ORDER BY day DESC LIMIT 15")
    fun get15NewestSpending(currentDay: Int, month: String): PagingSource<Int, SpendingEntity>

    @RawQuery(observedEntities = [SpendingEntity::class])
    fun getAllSpending(query: SupportSQLiteQuery): PagingSource<Int, SpendingEntity>

    @Query("SELECT * FROM spending WHERE id= :id")
    suspend fun getSpendingById(id: Int): SpendingEntity

    @Query("SELECT total FROM spending WHERE month=:month")
    suspend fun getThisMonthSpendingTotal(month: String): List<Int>

    @Query("SELECT total FROM spending WHERE month=:month AND year=:year")
    suspend fun getSpendingTotal(month: String, year: Int): List<Int>

    @Query("SELECT total FROM spending WHERE year=:year")
    suspend fun getAnnualSpendingTotal(year: Int): List<Int>

    @Query("DELETE FROM spending WHERE id=:id")
    suspend fun deleteSpending(id: Int)
}