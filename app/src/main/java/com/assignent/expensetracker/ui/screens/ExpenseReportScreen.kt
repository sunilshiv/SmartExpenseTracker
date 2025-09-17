package com.assignent.expensetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assignent.expensetracker.ui.viewmodels.ExpenseViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ExpenseReportScreen(viewModel: ExpenseViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    // call getReport usecase inside viewModel or separate ViewModel - for brevity using placeholder
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Last 7 days (mock)")
        Spacer(Modifier.height(8.dp))
        // You can render a simple bar chart using Canvas or use external chart lib
        Box(modifier = Modifier.height(200.dp).fillMaxWidth()) {
            Text("Chart placeholder")
        }
        Spacer(Modifier.height(8.dp))
        Text("Category wise totals (mock)")
    }
}