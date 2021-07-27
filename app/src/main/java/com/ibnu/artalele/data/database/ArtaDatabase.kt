package com.ibnu.artalele.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ibnu.artalele.data.dao.DebtDao
import com.ibnu.artalele.data.dao.IncomeDao
import com.ibnu.artalele.data.dao.SpendingDao
import com.ibnu.artalele.data.entities.DebtEntity
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.data.entities.SpendingEntity
import com.ibnu.artalele.utils.ConstValue.DATABASE_NAME

@Database(
    entities = [
        IncomeEntity::class,
        SpendingEntity::class,
        DebtEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class ArtaDatabase : RoomDatabase() {

    abstract fun getIncomeDao(): IncomeDao
    abstract fun getSpendingDao(): SpendingDao
    abstract fun getDebtDao(): DebtDao

    companion object {
        @Volatile
        private var INSTANCE: ArtaDatabase? = null

        fun getDatabase(context: Context): ArtaDatabase {
            if (INSTANCE == null) {
                synchronized(ArtaDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ArtaDatabase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE as ArtaDatabase
        }

    }

}