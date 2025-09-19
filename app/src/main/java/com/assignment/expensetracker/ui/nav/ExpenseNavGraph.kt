package com.assignment.expensetracker.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.assignment.expensetracker.ui.screens.ExpenseEntryScreen
import com.assignment.expensetracker.ui.screens.ExpenseListScreen
import com.assignment.expensetracker.ui.screens.ExpenseReportScreen
import com.assignment.expensetracker.ui.theme.ScreenScaffold

/**
 * Navigation graph for the Expense Tracker app.
 * Defines 3 routes:
 * - "list"   -> ExpenseListScreen
 * - "add"    -> ExpenseEntryScreen
 * - "report" -> ExpenseReportScreen
 */
@Composable
fun ExpenseNavGraph(navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination?.route

    NavHost(
        navController = navController,
        startDestination = "entry"
    ) {
        composable("entry") {
            ScreenScaffold(
                title = "Expenses",
                showBack = false, // 👈 no back arrow on list
                onBack = { navController.navigateUp() }
            ) {
                ExpenseEntryScreen(
                    onNavigateToList = { navController.navigate("list") }
                )
            }
        }
        composable("list") {
            ScreenScaffold(
                title = "View Expense",
                showBack = true,
                onBack = { navController.navigateUp() }
            ) {
                ExpenseListScreen(
                    onNavigateToReport = { navController.navigate("report") }
                )
            }
        }
        composable("report") {
            ScreenScaffold(
                title = "Expense Report",
                showBack = true,
                onBack = { navController.navigateUp() }
            ) {
                ExpenseReportScreen()
            }
        }
    }
}