package com.example.spendmoney.di

import android.content.Context
import androidx.room.Room
import com.example.spendmoney.data.local.DataBaseLocal
import org.koin.dsl.module

val databaseModule = module {
    single { provideLocalDatabase(get()) }
    single { provideObjSpendDAO(get()) }
    single { provideHistorySpendDao(get()) }
}
private fun provideLocalDatabase(context: Context): DataBaseLocal{
    return Room.databaseBuilder(
        context.applicationContext,
        DataBaseLocal::class.java,
        DataBaseLocal.NAME
    ).build()
}

private fun provideObjSpendDAO(local: DataBaseLocal) = local.ObjSpendDao
private fun provideHistorySpendDao(local: DataBaseLocal) = local.HistorySpendDao
