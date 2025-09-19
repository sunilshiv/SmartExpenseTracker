package com.assignment.expensetracker.data.repo

import com.assignment.expensetracker.data.model.Expense
import kotlinx.coroutines.flow.Flow

/**
 * Repository layer.
 * Decouples data sources (DB, network).
 */
interface ExpenseRepository {
    suspend fun addExpense(expense: Expense)
    fun expensesForDateFlow(date: Long): Flow<List<Expense>>
    fun allExpensesFlow(): Flow<List<Expense>>
    suspend fun deleteExpense(id: String)
}