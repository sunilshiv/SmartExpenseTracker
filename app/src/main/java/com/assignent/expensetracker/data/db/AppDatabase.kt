package com.assignent.expensetracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignent.expensetracker.data.model.Expense

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}