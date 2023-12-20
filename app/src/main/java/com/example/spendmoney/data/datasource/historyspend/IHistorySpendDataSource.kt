package com.example.spendmoney.data.datasource.historyspend

import com.example.spendmoney.models.HistorySpend

interface IHistorySpendDataSource {
    interface Local{
        suspend fun insertHistory(historySpend: HistorySpend): Long
        suspend fun getAllHistory(): List<HistorySpend>
    }

}