package com.assignment.expensetracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Expense entity stored in Room database.
 * Represents a single expense entry.
 */
@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val amount: Double,
    val category: String,
    val notes: String? = null,
    val receiptUri: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
