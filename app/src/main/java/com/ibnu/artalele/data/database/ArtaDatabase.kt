package com.ibnu.artalele.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ibnu.artalele.data.dao.DebtDao
import com.ibnu.artalele.data.dao.IncomeDao
import com.ibnu.artalele.data.dao.SpendingDao
import com.ibnu.artalele.data.entities.DebtEntity
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.data.entities.SpendingEntity
import com.ibnu.artalele.data.entities.fts.DebtFTS
import com.ibnu.artalele.utils.ConstValue.DATABASE_NAME

@Database(
    entities = [
        IncomeEntity::class,
        SpendingEntity::class,
        DebtEntity::class,
        DebtFTS::class,
    ],
    version = 2,
    exportSchema = true
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
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    db.execSQL("INSERT INTO debt_fts(debt_fts) VALUES ('rebuild')")
                                }
                            })
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE as ArtaDatabase
        }

    }

}