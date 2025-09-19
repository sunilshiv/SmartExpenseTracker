package com.assignment.expensetracker.domain.usecase

import com.assignment.expensetracker.data.model.Expense
import com.assignment.expensetracker.data.repo.ExpenseRepository
import javax.inject.Inject

/**
 * AddExpenseUseCase - wraps repository call
 */
class AddExpenseUseCase @Inject constructor(
    private val repo: ExpenseRepository
) {
    suspend operator fun invoke(expense: Expense) {
        repo.addExpense(expense)
    }
}