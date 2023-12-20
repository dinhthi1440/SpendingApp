package com.example.spendmoney.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spendmoney.data.local.dao.HistoryDAO
import com.example.spendmoney.data.local.dao.ObjSpendDAO
import com.example.spendmoney.models.HistorySpend
import com.example.spendmoney.models.ObjSpend

@Database(
    entities = [ObjSpend::class, HistorySpend::class],
    version = DataBaseLocal.VERSION
)
abstract class DataBaseLocal : RoomDatabase() {

    abstract val ObjSpendDao : ObjSpendDAO
    abstract val HistorySpendDao : HistoryDAO
    companion object {
        const val NAME = "SpendMoney"
        const val VERSION = 1
        const val TABLE_OBJSPEND = "ObjSpend"
        const val TABLE_HISTORY = "HistorySpend"
    }
}
