package com.example.spendmoney.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spendmoney.data.local.DataBaseLocal
import com.example.spendmoney.models.HistorySpend

@Dao
interface HistoryDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(historySpend: HistorySpend): Long

    @Query("SELECT * FROM ${DataBaseLocal.TABLE_HISTORY}")
    fun getAllHistory(): List<HistorySpend>
}