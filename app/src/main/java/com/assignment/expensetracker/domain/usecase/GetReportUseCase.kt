package com.assignment.expensetracker.domain.usecase

import com.assignment.expensetracker.data.model.Expense
import com.assignment.expensetracker.data.repo.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

data class DailyTotal(val dateMillis: Long, val total: Double)
data class CategoryTotal(val category: String, val total: Double)

class GetReportUseCase @Inject constructor(
    private val repo: ExpenseRepository
) {
    operator fun invoke(): Flow<Pair<List<DailyTotal>, List<CategoryTotal>>> {
        return repo.allExpensesFlow().map { expenses: List<Expense> ->

            val now = System.currentTimeMillis()
            val sevenDaysAgo = now - 7L * 24 * 60 * 60 * 1000

            // ✅ Kotlin's List functions now available
            val last7 = expenses.filter { it.timestamp >= sevenDaysAgo }

            // Daily totals (grouped by date)
            val daily = last7
                .groupBy { dayStartMillis(it.timestamp) }
                .map { (day, list) ->
                    DailyTotal(day, list.sumOf { it.amount })
                }
                .sortedBy { it.dateMillis }

            // Category totals
            val category = last7
                .groupBy { it.category }
                .map { (cat, list) ->
                    CategoryTotal(cat, list.sumOf { it.amount })
                }

            daily to category
        }
    }

    private fun dayStartMillis(ts: Long): Long {
        val cal = java.util.Calendar.getInstance().apply {
            timeInMillis = ts
            set(java.util.Calendar.HOUR_OF_DAY, 0)
            set(java.util.Calendar.MINUTE, 0)
            set(java.util.Calendar.SECOND, 0)
            set(java.util.Calendar.MILLISECOND, 0)
        }
        return cal.timeInMillis
    }
}