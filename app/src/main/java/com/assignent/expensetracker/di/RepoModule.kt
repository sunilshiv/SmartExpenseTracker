package com.assignent.expensetracker.di

import android.content.Context
import androidx.room.Room
import com.assignent.expensetracker.data.db.AppDatabase
import com.assignent.expensetracker.data.repo.ExpenseRepository
import com.assignent.expensetracker.data.repo.RoomExpenseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindExpenseRepository(impl: RoomExpenseRepository): ExpenseRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase {
        return Room.databaseBuilder(ctx, AppDatabase::class.java, "expense_db")
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideExpenseDao(db: AppDatabase) = db.expenseDao()
}