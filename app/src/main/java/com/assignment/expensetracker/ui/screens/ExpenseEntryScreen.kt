package com.assignment.expensetracker.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.assignment.expensetracker.data.model.Expense
import com.assignment.expensetracker.ui.viewmodels.ExpenseViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ExpenseEntryScreen(
    viewModel: ExpenseViewModel = hiltViewModel(),
    onNavigateToList: () -> Unit
) {
    val ctx = LocalContext.current
    val state by viewModel.uiState.collectAsState()
    var title by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Staff") }
    var notes by remember { mutableStateOf("") }

    // sample submit animation: scale on submit
    val animateScale by remember { mutableStateOf(1f) }
    val scale = animateFloatAsState(targetValue = animateScale)

    Column(modifier = androidx.compose.ui.Modifier.padding(16.dp)) {
        Text("Total Spent Today: ₹${state.totalToday}", style = MaterialTheme.typography.titleLarge )
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = amountText, onValueChange = { amountText = it }, label = { Text("Amount (₹)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(Modifier.height(8.dp))
        Row {
            listOf("Staff", "Travel", "Food", "Utility").forEach { cat ->
                Button(onClick = { category = cat }, modifier = androidx.compose.ui.Modifier.padding(end = 8.dp)) {
                    Text(cat)
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = notes, onValueChange = { if (it.length <= 100) notes = it }, label = { Text("Notes (optional)") })
        Spacer(Modifier.height(12.dp))
        Row {
            Button(onClick = {
                val amt = amountText.toDoubleOrNull() ?: 0.0
                val expense = Expense(title = title, amount = amt, category = category, notes = notes)
                viewModel.addExpense(expense)
            }) {
                Text("Submit")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = onNavigateToList) { Text("View List") }
        }
    }

    LaunchedEffect(state.message) {
        state.message?.let {
            Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()
            viewModel.clearMessage()
        }
    }
}