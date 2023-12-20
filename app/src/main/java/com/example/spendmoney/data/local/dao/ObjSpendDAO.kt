package com.example.spendmoney.data.local.dao


import androidx.room.*
import com.example.spendmoney.data.local.DataBaseLocal
import com.example.spendmoney.models.HistorySpend
import com.example.spendmoney.models.ObjSpend
import io.reactivex.rxjava3.core.Single

@Dao
interface ObjSpendDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ObjSpend: ObjSpend): Long

    @Query("SELECT * FROM ${DataBaseLocal.TABLE_OBJSPEND}")
    fun getAll(): List<ObjSpend>

    @Query("SELECT * FROM ${DataBaseLocal.TABLE_OBJSPEND} WHERE id =:idObj")
    fun getObjById(idObj: String): ObjSpend

    @Query("UPDATE ${DataBaseLocal.TABLE_OBJSPEND} SET MoneyDaTieu = MoneyDaTieu +:TienTieu WHERE id =:idObj")
    fun updateMoneyObjSpendById(idObj: String, TienTieu: Double): Int
}