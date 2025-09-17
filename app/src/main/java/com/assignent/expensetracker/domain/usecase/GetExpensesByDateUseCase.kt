package com.assignent.expensetracker.domain.usecase

import com.assignent.expensetracker.data.model.Expense
import com.assignent.expensetracker.data.repo.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExpensesByDateUseCase @Inject constructor(
    private val repo: ExpenseRepository
) {
    operator fun invoke(dateMillis: Long): Flow<List<Expense>> {
        return repo.expensesForDateFlow(dateMillis)
    }
}