package com.ibnu.artalele.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income")
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
    val weight: String,
    val total: Int,
    val description: String?
)
