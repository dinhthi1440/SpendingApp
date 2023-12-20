package com.example.spendmoney.data.datasource.historyspend

import com.example.spendmoney.data.local.DataBaseLocal
import com.example.spendmoney.models.HistorySpend

class HistorySpendDataSource(private val dataBaseLocal: DataBaseLocal) :IHistorySpendDataSource.Local{
    override suspend fun insertHistory(historySpend: HistorySpend): Long {
        return dataBaseLocal.HistorySpendDao.insertHistory(historySpend)
    }

    override suspend fun getAllHistory(): List<HistorySpend> {
        return dataBaseLocal.HistorySpendDao.getAllHistory()
    }
}