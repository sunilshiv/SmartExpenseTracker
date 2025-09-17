package com.assignent.expensetracker.data.repo

import com.assignent.expensetracker.data.model.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun addExpense(expense: Expense)
    fun expensesForDateFlow(date: Long): Flow<List<Expense>>
    fun allExpensesFlow(): Flow<List<Expense>>
    suspend fun deleteExpense(id: String)
}