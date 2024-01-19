package com.example.spendmoney.data.repository.historyspend

import com.example.spendmoney.base.BaseReponsitory
import com.example.spendmoney.base.DataResult
import com.example.spendmoney.data.datasource.historyspend.IHistorySpendDataSource
import com.example.spendmoney.models.HistorySpend

class HistorySpendRepos(private val local: IHistorySpendDataSource.Local): BaseReponsitory(), IHistorySpendRepos {
    override suspend fun insertHistory(historySpend: HistorySpend): DataResult<Long> {
        return getResult { local.insertHistory(historySpend) }
    }

    override suspend fun getAllHistory(): DataResult<List<HistorySpend>> {
        return getResult { local.getAllHistory() }
    }

    override suspend fun getTotalMoneyExpense(): DataResult<Double> {
        return getResult { local.getTotalMoneyExpense() }
    }

    override suspend fun getSpendByMonth(month: String): DataResult<List<HistorySpend>> {
        return getResult { local.getSpendByMonth(month) }
    }

    override suspend fun deleteHistorySpend(idHistorySpend: String): DataResult<Int> {
        return getResult { local.deleteHistorySpend(idHistorySpend) }
    }
}