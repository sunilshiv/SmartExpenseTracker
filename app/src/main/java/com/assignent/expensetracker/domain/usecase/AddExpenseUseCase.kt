package com.assignent.expensetracker.domain.usecase

import com.assignent.expensetracker.data.model.Expense
import com.assignent.expensetracker.data.repo.ExpenseRepository
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(
    private val repo: ExpenseRepository
) {
    suspend operator fun invoke(expense: Expense) {
        repo.addExpense(expense)
    }
}