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
| Expense List | Add Expense | Expense Report |
|--------------|-------------|----------------|
| ![List](docs/add_expense.png) | ![Entry](docs/view_expense_category.png) | ![Report](docs/view_expense_chart.png) |

---

## ⚙️ Setup & Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/smart-expense-tracker.git
   cd smart-expense-tracker
