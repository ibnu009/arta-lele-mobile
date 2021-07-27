package com.ibnu.artalele.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spending")
data class SpendingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val total: Int,
    val category: String,
    val description: String
)
