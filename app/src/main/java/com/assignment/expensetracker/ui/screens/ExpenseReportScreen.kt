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
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
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
fun DailyTotalsBarChart(dailyTotals: List<DailyTotal>) {
    if (dailyTotals.isEmpty()) {
        androidx.compose.material3.Text("No daily data available")
        return
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        factory = { context ->
            BarChart(context).apply {
                description.isEnabled = false
                setFitBars(true)
                axisRight.isEnabled = false
                legend.isEnabled = false
            }
        },
        update = { barChart ->
            val entries = dailyTotals.mapIndexed { index, daily ->
                BarEntry(index.toFloat(), daily.total.toFloat())
            }

            val dataSet = BarDataSet(entries, "Daily Totals").apply {
                colors = ColorTemplate.MATERIAL_COLORS.toList()
                valueTextSize = 12f
            }

            val data = BarData(dataSet).apply { barWidth = 0.5f }
            barChart.data = data

            // Format x-axis labels as day names
            val formatter = SimpleDateFormat("EEE", Locale.getDefault())
            val labels = dailyTotals.map { formatter.format(Date(it.dateMillis)) }

            barChart.xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(labels)
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                granularity = 1f
                labelRotationAngle = -30f
            }

            barChart.axisLeft.apply {
                axisMinimum = 0f
                setDrawGridLines(true)
            }

            barChart.invalidate()
        }
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
