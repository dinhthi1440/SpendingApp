package com.example.spendmoney.di

import com.example.spendmoney.ui.history.ShowHistoryViewModel
import com.example.spendmoney.ui.home.HomeViewModel
import com.example.spendmoney.ui.managementExpense.ManagementExpenseViewModel
import com.example.spendmoney.ui.newObjSPend.NewObjSpendViewModel
import com.example.spendmoney.ui.objspend.ObjSpendViewModel
import com.example.spendmoney.ui.organizingExpense.OrganizingExpenseViewModel
import com.example.spendmoney.ui.spendInput.InputSpendViewModel
import com.example.spendmoney.ui.statisticalExpense.StatisticalExpenseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ObjSpendViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel {InputSpendViewModel(get(), get())}
    viewModel { ShowHistoryViewModel(get(), get()) }
    viewModel { ManagementExpenseViewModel(get()) }
    viewModel {NewObjSpendViewModel(get())}
    viewModel {StatisticalExpenseViewModel(get())}
    viewModel {OrganizingExpenseViewModel(get())}
}