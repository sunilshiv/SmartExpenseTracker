package com.assignment.expensetracker.domain.usecase

import com.assignment.expensetracker.data.model.Expense
import com.assignment.expensetracker.data.repo.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExpensesByDateUseCase @Inject constructor(
    private val repo: ExpenseRepository
) {
    operator fun invoke(dateMillis: Long): Flow<List<Expense>> {
        return repo.expensesForDateFlow(dateMillis)
    }
}