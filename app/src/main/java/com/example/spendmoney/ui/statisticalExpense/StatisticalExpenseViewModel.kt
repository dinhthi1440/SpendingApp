package com.example.spendmoney.ui.statisticalExpense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.historyspend.IHistorySpendRepos
import com.example.spendmoney.models.HistorySpend

class StatisticalExpenseViewModel(private val historySpendRepos: IHistorySpendRepos):BaseViewModel() {
    private val _spendByMonth = MutableLiveData<List<HistorySpend>>()
    val spendByMonth: LiveData<List<HistorySpend>> get() = _spendByMonth

    fun getSpendByDay(month: String) {
        executeTask(
            request = {historySpendRepos.getSpendByMonth(month)},
            onSuccess = {
                _spendByMonth.value = it
            },
            onError = { onErrorProcess(it)}
        )
    }
}