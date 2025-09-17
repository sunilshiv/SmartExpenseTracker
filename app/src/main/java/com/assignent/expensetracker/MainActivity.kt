package com.assignent.expensetracker

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assignent.expensetracker.ui.nav.ExpenseNavGraph
import com.assignent.expensetracker.ui.screens.ExpenseEntryScreen
import com.assignent.expensetracker.ui.screens.ExpenseListScreen
import com.assignent.expensetracker.ui.screens.ExpenseReportScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Surface(color = MaterialTheme.colorScheme.background) {
                NavHost(
                    navController = navController,
                    startDestination = "entry"
                ) {
                    composable("entry") {
                        ExpenseEntryScreen(
                            onNavigateToList = { navController.navigate("list") }
                        )
                    }
                    composable("list") {
                        ExpenseListScreen(
                            onNavigateToReport = { navController.navigate("report") }
                        )
                    }
                    composable("report") {
                        ExpenseReportScreen()
                    }
                }
            }
        }
    }
}

/*
@Composable
fun ExpenseApp() {
    val navController = rememberNavController()

    Surface(color = Resources.Theme.colors.background) {
        NavHost(
            navController = navController,
            startDestination = "entry"   // ✅ Start at ExpenseEntryScreen
        ) {
            composable("entry") {
                ExpenseEntryScreen(onNavigateToList = { navController.navigate("list") })
            }
            composable("list") {
                ExpenseListScreen(onNavigateToReport = { navController.navigate("report") })
            }
            composable("report") {
                ExpenseReportScreen()
            }
        }
    }
}*/
