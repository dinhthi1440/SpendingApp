package com.example.spendmoney.data.datasource.historyspend

import com.example.spendmoney.models.HistorySpend

interface IHistorySpendDataSource {
    interface Local{
        suspend fun insertHistory(historySpend: HistorySpend): Long
        suspend fun getAllHistory(): List<HistorySpend>
        suspend fun getTotalMoneyExpense(): Double
        suspend fun getSpendByMonth(month: String): List<HistorySpend>
        suspend fun deleteHistorySpend(idHistorySpend: String): Int
    }

}