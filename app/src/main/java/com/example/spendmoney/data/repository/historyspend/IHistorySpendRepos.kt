package com.example.spendmoney.data.repository.historyspend

import com.example.spendmoney.base.DataResult
import com.example.spendmoney.models.HistorySpend

interface IHistorySpendRepos {
    suspend fun insertHistory(historySpend: HistorySpend): DataResult<Long>
    suspend fun getAllHistory(): DataResult<List<HistorySpend>>
}