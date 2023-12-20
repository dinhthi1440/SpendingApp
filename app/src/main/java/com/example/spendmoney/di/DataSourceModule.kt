package com.example.spendmoney.di

import com.example.spendmoney.data.datasource.historyspend.HistorySpendDataSource
import com.example.spendmoney.data.datasource.historyspend.IHistorySpendDataSource
import com.example.spendmoney.data.datasource.objspend.IObjSpendDataSource
import com.example.spendmoney.data.datasource.objspend.ObjSpendLocalDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<IObjSpendDataSource.Local> { ObjSpendLocalDataSource(get()) }
    single<IHistorySpendDataSource.Local>{HistorySpendDataSource(get())}
}