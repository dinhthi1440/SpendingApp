package com.example.spendmoney.data.datasource.objspend

import com.example.spendmoney.models.ObjSpend
import io.reactivex.rxjava3.core.Single

interface IObjSpendDataSource {
    interface Local{
        suspend fun insertObjSpend(objSpend: ObjSpend): Long
        suspend fun getAllObjSpend(): List<ObjSpend>
        suspend fun getObjSpendById(id: String): ObjSpend
        suspend fun updateMoneySpend(idObj: String, TienTieu: Double): Int
        suspend fun getTotalMoney(): Double
        suspend fun deleteObjSpend(idObj: String): Int
        suspend fun getTotalMoneyExpense(): Double
        suspend fun refundMoneyObjSpendById(idObjSpend: String, TienTieu: Double): Int
        suspend fun updateObjSpendById(id: String, newName: String, newMoneyBanDau: Double, newImgObjSpend: Int): Int
    }
}