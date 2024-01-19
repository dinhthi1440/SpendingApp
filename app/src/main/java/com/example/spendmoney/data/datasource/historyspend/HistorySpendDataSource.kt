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

    override suspend fun getTotalMoneyExpense(): Double {
        return dataBaseLocal.HistorySpendDao.getTotalMoneyExpense()
    }

    override suspend fun getSpendByMonth(month: String): List<HistorySpend> {
        return dataBaseLocal.HistorySpendDao.getSpendByMonth(month)
    }

    override suspend fun deleteHistorySpend(idHistorySpend: String): Int {
        return dataBaseLocal.HistorySpendDao.deleteHistorySpend(idHistorySpend)
    }
}