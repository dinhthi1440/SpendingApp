package com.example.spendmoney.di

import com.example.spendmoney.ui.history.ShowHistoryViewModel
import com.example.spendmoney.ui.home.HomeViewModel
import com.example.spendmoney.ui.objspend.ObjSpendViewModel
import com.example.spendmoney.ui.spendInput.InputSpendViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ObjSpendViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel {InputSpendViewModel(get())}
    viewModel { ShowHistoryViewModel(get()) }
}