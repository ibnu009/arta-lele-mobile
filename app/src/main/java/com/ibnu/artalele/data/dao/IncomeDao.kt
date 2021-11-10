package com.ibnu.artalele.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.ibnu.artalele.data.entities.IncomeEntity

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncome(income: IncomeEntity)

    @Update
    suspend fun updateIncome(income: IncomeEntity)

    @RawQuery(observedEntities = [IncomeEntity::class])
    fun getAllIncome(query: SupportSQLiteQuery): PagingSource<Int, IncomeEntity>

    @Query("SELECT * FROM income WHERE day= :currentDay AND month=:month ORDER BY day DESC LIMIT 15")
    fun getOnly15NewestIncomes(currentDay: Int, month: String): PagingSource<Int, IncomeEntity>

    @Query("SELECT total FROM income WHERE month= :month AND year= :year")
    suspend fun getIncomeTotalByMonthAndYear(month: String, year: Int): List<Int>

    @Query("SELECT total FROM income WHERE year=:year")
    suspend fun getAnnualIncomeTotal(year: Int): List<Int>

    @Query("SELECT * FROM income WHERE id= :id")
    suspend fun getIncomeById(id: Int): IncomeEntity

    @Query("DELETE FROM income WHERE id = :id")
    suspend fun deleteIncome(id: Int)

}