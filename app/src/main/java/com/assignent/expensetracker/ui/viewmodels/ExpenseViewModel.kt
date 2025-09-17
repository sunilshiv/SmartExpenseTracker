package com.assignent.expensetracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignent.expensetracker.data.model.Expense
import com.assignent.expensetracker.domain.usecase.AddExpenseUseCase
import com.assignent.expensetracker.domain.usecase.GetExpensesByDateUseCase
import com.assignent.expensetracker.domain.usecase.GetReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExpenseUiState(
    val todaysExpenses: List<Expense> = emptyList(),
    val totalToday: Double = 0.0,
    val message: String? = null,
    val loading: Boolean = true,
    val groupByCategory: Boolean = false // 👈 added back
)

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val addExpenseUseCase: AddExpenseUseCase,
    private val getExpensesByDateUseCase: GetExpensesByDateUseCase,
    private val getReportUseCase: GetReportUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseUiState())
    val uiState: StateFlow<ExpenseUiState> = _uiState.asStateFlow()

    private val selectedDate = MutableStateFlow(System.currentTimeMillis())

    init {
        selectedDate
            .flatMapLatest { date -> getExpensesByDateUseCase(date) }
            .onEach { expenses ->
                val total = expenses.sumOf { it.amount }
                _uiState.update {
                    it.copy(
                        todaysExpenses = expenses,
                        totalToday = total,
                        loading = false
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            if (expense.title.isBlank() || expense.amount <= 0.0) {
                _uiState.update { it.copy(message = "Title required and amount > 0") }
                return@launch
            }
            addExpenseUseCase(expense)
            _uiState.update { it.copy(message = "Expense added") }
        }
    }

    fun clearMessage() {
        _uiState.update { it.copy(message = null) }
    }

    fun setDate(dateMillis: Long) {
        selectedDate.value = dateMillis
    }

    // 👇 Added back
    fun toggleGroupBy() {
        _uiState.update { it.copy(groupByCategory = !it.groupByCategory) }
    }
}