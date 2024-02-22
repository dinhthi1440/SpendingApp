package com.example.spendmoney.data.repository.historyspend

import com.example.spendmoney.base.DataResult
import com.example.spendmoney.models.HistorySpend

interface IHistorySpendRepos {
    suspend fun insertHistory(historySpend: HistorySpend): DataResult<Long>
    suspend fun getAllHistory(): DataResult<List<HistorySpend>>
    suspend fun getTotalMoneyExpense(): DataResult<Double>
    suspend fun getSpendByMonthYear(month: String, year: String): DataResult<List<HistorySpend>>
    suspend fun deleteHistorySpend(idHistorySpend: String): DataResult<Int>
}