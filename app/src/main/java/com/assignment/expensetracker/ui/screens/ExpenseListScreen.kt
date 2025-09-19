package com.assignment.expensetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assignment.expensetracker.data.model.Expense
import com.assignment.expensetracker.ui.viewmodels.ExpenseViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Screen that displays expenses.
 * Toggle: group by category or by date
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseListScreen(
    onNavigateToReport: () -> Unit,
    viewModel: ExpenseViewModel = hiltViewModel()

) {
    val state by viewModel.uiState.collectAsState()

    /**
     * Scaffold displays with TopAppBar
     * Supports title and actions
     */
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expenses") },
                actions = {
                    TextButton(onClick = { viewModel.toggleGroupBy() }) {
                        Text(
                            if (state.groupByCategory) "By Time" else "By Category",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            )

        },

        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToReport) {
                Text("View Report",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (state.todaysExpenses.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text("No expenses found")
                }
            } else {
                if (state.groupByCategory) {
                    ExpensesByCategory(state.todaysExpenses)
                } else {
                    ExpensesByDateTime(state.todaysExpenses)
                }
            }
        }
    }
}

@Composable
private fun ExpensesByCategory(expenses: List<Expense>) {
    val grouped = expenses.groupBy { it.category }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        grouped.forEach { (category, items) ->
            item {
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
            items(items) { expense ->
                ExpenseItem(expense)
            }
        }
    }
}

@Composable
private fun ExpensesByDateTime(expenses: List<Expense>) {
    // Sort by timestamp descending (latest first)
    val sorted = expenses.sortedByDescending { it.timestamp }
    val formatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(sorted) { expense ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = formatter.format(Date(expense.timestamp)),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                ExpenseItem(expense = expense)
            }
        }
    }
}


@Composable
private fun ExpenseItem(expense: Expense) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(expense.title, style = MaterialTheme.typography.bodyLarge)
                Text("₹${expense.amount}", style = MaterialTheme.typography.bodyMedium)
            }
            Text(expense.category, style = MaterialTheme.typography.labelMedium)
        }
    }
}