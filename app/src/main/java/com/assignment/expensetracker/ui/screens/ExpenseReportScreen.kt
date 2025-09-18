package com.assignment.expensetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.assignment.expensetracker.domain.usecase.CategoryTotal
import com.assignment.expensetracker.domain.usecase.DailyTotal
import com.assignment.expensetracker.ui.viewmodels.ExpenseViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseReportScreen(viewModel: ExpenseViewModel = hiltViewModel()) {
    val report by viewModel.reportState.collectAsState(initial = Pair(emptyList(), emptyList()))
    val (dailyTotals, categoryTotals) = report

    Scaffold(
        topBar = { TopAppBar(title = { Text("Expense Report") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Last 7 Days", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            DailyTotalsBarChart(dailyTotals)
            Spacer(Modifier.height(24.dp))
            Text("By Category", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            CategoryPieChart(categoryTotals)
        }
    }
}

@Composable
private fun DailyTotalsBarChart(dailyTotals: List<DailyTotal>) {
    if (dailyTotals.isEmpty()) {
        Text("No daily data")
        return
    }
    val entries = dailyTotals.mapIndexed { idx, dt -> entryOf(idx.toFloat(), dt.total.toFloat()) }
    val labels = dailyTotals.map { SimpleDateFormat("EEE", Locale.getDefault()).format(Date(it.dateMillis)) }
    val modelProducer = ChartEntryModelProducer(entries).requireModel()
    Chart(
        chart = columnChart(),
        model = modelProducer,
        startAxis = rememberStartAxis(),
        bottomAxis = rememberBottomAxis(
            valueFormatter = { x, _ ->
                labels.getOrNull(x.toInt()) ?: ""
            }
        ),
        modifier = Modifier.fillMaxWidth().height(220.dp)
    )
}

@Composable
private fun CategoryPieChart(categoryTotals: List<CategoryTotal>) {
    if (categoryTotals.isEmpty()) {
        Text("No category data")
        return
    }
    AndroidView(
        factory = { context ->
            PieChart(context).apply {
                description.isEnabled = false
                isDrawHoleEnabled = true
                setUsePercentValues(true)
                legend.isEnabled = true
            }
        },
        update = { pieChart ->
            val entries = categoryTotals.map { PieEntry(it.total.toFloat(), it.category) }
            val dataSet = PieDataSet(entries, "Categories")
            dataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
            val data = PieData(dataSet).apply { setDrawValues(true) }
            pieChart.data = data
            pieChart.invalidate()
        },
        modifier = Modifier.fillMaxWidth().height(300.dp)
    )
}
