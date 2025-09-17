package com.assignent.expensetracker.data.repo

import com.assignent.expensetracker.data.db.ExpenseDao
import com.assignent.expensetracker.data.model.Expense
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomExpenseRepository @Inject constructor(
    private val dao: ExpenseDao
): ExpenseRepository {
    override suspend fun addExpense(expense: Expense) = dao.insert(expense)
    override fun expensesForDateFlow(date: Long): Flow<List<Expense>> = dao.getExpensesForDateFlow(date)
    override fun allExpensesFlow(): Flow<List<Expense>> = dao.getAllFlow()
    override suspend fun deleteExpense(id: String) = dao.deleteById(id)
}