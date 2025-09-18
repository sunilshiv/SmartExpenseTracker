package com.assignment.expensetracker.data.db

import androidx.room.*
import com.assignment.expensetracker.data.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Query("SELECT * FROM expenses WHERE date(timestamp/1000, 'unixepoch') = date(:date/1000, 'unixepoch') ORDER BY timestamp DESC")
    fun getExpensesForDateFlow(date: Long): Flow<List<Expense>>

    @Query("SELECT * FROM expenses ORDER BY timestamp DESC")
    fun getAllFlow(): Flow<List<Expense>>

    @Query("DELETE FROM expenses WHERE id = :id")
    suspend fun deleteById(id: String)
}