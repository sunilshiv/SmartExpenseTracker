package com.assignent.expensetracker.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.assignent.expensetracker.ui.screens.ExpenseEntryScreen
import com.assignent.expensetracker.ui.screens.ExpenseListScreen
import com.assignent.expensetracker.ui.screens.ExpenseReportScreen

@Composable
fun ExpenseNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "entry") {
        composable("entry") { ExpenseEntryScreen(onNavigateToList = { navController.navigate("list") }) }
        composable("list") { ExpenseListScreen(onNavigateToReport = { navController.navigate("report") }) }
        composable("report") { ExpenseReportScreen() }
    }
}