package com.assignment.expensetracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.expensetracker.data.model.Expense

/**
 * Room Database holder.
 * Provides access to DAO.
 */
@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}