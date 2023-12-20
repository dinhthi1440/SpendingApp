package com.example.spendmoney.di

import com.example.spendmoney.data.repository.historyspend.HistorySpendRepos
import com.example.spendmoney.data.repository.historyspend.IHistorySpendRepos
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.data.repository.objspend.ObjSpendRepos
import org.koin.dsl.module

val repositoryModule = module {
    single<IObjSpendRepos> { ObjSpendRepos(get()) }
    single<IHistorySpendRepos> { HistorySpendRepos(get()) }
}