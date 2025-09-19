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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.assignment.expensetracker.data.model.Expense
import com.assignment.expensetracker.ui.viewmodels.ExpenseViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.assignment.expensetracker.ui.theme.CategoryDropdown

/**
 * Screen to add a new expense.
 * Includes:
 * - Title input
 * - Amount input
 * - Category dropdown
 * - Submit button
 */
@Composable
fun ExpenseEntryScreen(
    viewModel: ExpenseViewModel = hiltViewModel(),
    onNavigateToList: () -> Unit
) {
    // Local states for inputs
    val ctx = LocalContext.current
    val state by viewModel.uiState.collectAsState()
    var title by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Staff") }
    var notes by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Food") }
    val keyboardController = LocalSoftwareKeyboardController.current

    // sample submit animation: scale on submit
    val animateScale by remember { mutableStateOf(1f) }
    val scale = animateFloatAsState(targetValue = animateScale)

    Column(modifier = androidx.compose.ui.Modifier.padding(16.dp)) {
        Text("Add Expenses:", style = MaterialTheme.typography.titleLarge )
        Spacer(Modifier.height(12.dp))
        Text("Total Spent Today: ₹${state.totalToday}", style = MaterialTheme.typography.titleLarge )
        Spacer(Modifier.height(12.dp))

        // Title input
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        Spacer(Modifier.height(8.dp))

        // Amount input
        OutlinedTextField(value = amountText, onValueChange = { amountText = it }, label = { Text("Amount (₹)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(Modifier.height(8.dp))

        // Category dropdown
        CategoryDropdown(
            categories = listOf("Food", "Transport", "Shopping", "Bills", "Other"),
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = notes, onValueChange = { if (it.length <= 100) notes = it }, label = { Text("Notes (optional)") })
        Spacer(Modifier.height(12.dp))
        Row {
            //Submit button
            Button(onClick = {
                val amt = amountText.toDoubleOrNull() ?: 0.0
                val expense = Expense(title = title, amount = amt, category = category, notes = notes)

                // Add expense
                viewModel.addExpense(expense)
                // Hide keyboard
                keyboardController?.hide()
            }) {
                Text("Submit")
            }
            Spacer(Modifier.width(8.dp))

            // Navigate back to list
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