package com.assignent.expensetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assignent.expensetracker.ui.viewmodels.ExpenseViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ExpenseListScreen(
    viewModel: ExpenseViewModel = hiltViewModel(),
    onNavigateToReport: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    Column(modifier = androidx.compose.ui.Modifier.padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = androidx.compose.ui.Modifier.fillMaxWidth()) {
            Text("Expenses (${state.todaysExpenses.size})")
            Text("Total: ₹${state.totalToday}")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.toggleGroupBy() }) { Text(if (state.groupByCategory) "Group by Time" else "Group by Category") }
        Spacer(Modifier.height(8.dp))
        if (state.todaysExpenses.isEmpty()) {
            Text("No expenses for selected date")
        } else {
            state.todaysExpenses.forEach { e ->
                Card(modifier = androidx.compose.ui.Modifier.fillMaxWidth().padding(4.dp)) {
                    Column(modifier = androidx.compose.ui.Modifier.padding(8.dp)) {
                        Text(e.title)
                        Spacer(Modifier.height(4.dp))
                        Text("${e.category} • ₹${e.amount}")
                        Spacer(Modifier.height(2.dp))
                        Text(SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(e.timestamp)))
                    }
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        Button(onClick = onNavigateToReport) { Text("Report") }
    }
}