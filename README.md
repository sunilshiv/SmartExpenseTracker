# 📊 Smart Expense Tracker

A modern **Expense Tracking App** built with **Jetpack Compose**, **Clean MVVM Architecture**, and **Material 3**, designed to showcase clean code practices, modular architecture, and state-of-the-art Android libraries.

---

## 🚀 Features

- ✨ Add, view, and categorize expenses
- 📅 Group expenses **by date & time** or **by category**
- 📊 Interactive charts using **MPAndroidChart**
- 💾 Offline storage with **Room Database**
- 🧩 Multi-module **Clean Architecture** (data, domain, ui)
- 🔄 Reactive state management with **StateFlow**
- 🎨 Beautiful UI with **Material 3** and dynamic theming
- 📱 Built fully in **Jetpack Compose**

---

## 🏗️ Architecture

This project follows **Clean MVVM Architecture** with **UseCases**:

app
├── data
│    ├── db
│    │    ├── AppDatabase.kt
│    │    └── ExpenseDao.kt
│    ├── model
│    │    └── Expense.kt
│    └── repository
│         └── ExpenseRepository.kt
├── di
│    └── AppModule.kt
├── domain
│    ├── model
│    │    └── ReportModels.kt
│    └── usecase
│         ├── AddExpenseUseCase.kt
│         ├── GetExpensesByDateUseCase.kt
│         └── GetReportUseCase.kt
├── ui
│    ├── components
│    │    ├── CategoryDropdown.kt
│    │    └── ScreenScaffold.kt
│    ├── navigation
│    │    └── ExpenseNavGraph.kt
│    ├── screens
│    │    ├── ExpenseEntryScreen.kt
│    │    ├── ExpenseListScreen.kt
│    │    └── ExpenseReportScreen.kt
│    └── viewmodels
│         └── ExpenseViewModel.kt
└── MainActivity.kt



- **UI Layer**: Jetpack Compose screens (Stateless UIs)
- **ViewModel Layer**: Exposes immutable state via `StateFlow`
- **Domain Layer**: Business logic with UseCases (`AddExpenseUseCase`, `GetExpensesByDateUseCase`, `GetReportUseCase`)
- **Data Layer**: Room DB, Repositories

---

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose, Material 3
- **Navigation**: Navigation-Compose
- **Dependency Injection**: Hilt
- **Database**: Room
- **Charting**: MPAndroidChart
- **Async**: Kotlin Coroutines, Flow/StateFlow

---

## 📸 Screenshots

_Add screenshots here once the app runs_
![add_expense.png](..%2FExpenseTrackScreenshot%2Fadd_expense.png)
![view_expense_category.png](..%2FExpenseTrackScreenshot%2Fview_expense_category.png)
![view_expense_by_time.png](..%2FExpenseTrackScreenshot%2Fview_expense_by_time.png)
![view_expense_chart.png](..%2FExpenseTrackScreenshot%2Fview_expense_chart.png)
| Expense List | Add Expense | Expense Report |
|--------------|-------------|----------------|
| ![List](docs/list.png) | ![Entry](docs/add.png) | ![Report](docs/report.png) |

---

## ⚙️ Setup & Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/smart-expense-tracker.git
   cd smart-expense-tracker
