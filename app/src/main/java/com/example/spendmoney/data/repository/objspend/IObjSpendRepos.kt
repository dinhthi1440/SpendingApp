package com.example.spendmoney.data.repository.objspend

import com.example.spendmoney.base.DataResult
import com.example.spendmoney.models.ObjSpend
import io.reactivex.rxjava3.core.Single

interface IObjSpendRepos {
    suspend fun insertObjSpend(objSpend: ObjSpend): DataResult<Long>
    suspend fun getAllObjSpend(): DataResult<List<ObjSpend>>
    suspend fun getObjSpendById(id: String): DataResult<ObjSpend>
    suspend fun updateMoneyObjSpendById(idObj: String, TienTieu: Double): DataResult<Int>
    suspend fun getTotalMoney(): DataResult<Double>
    suspend fun deleteObjSpend(idObj: String): DataResult<Int>
    suspend fun getTotalMoneyExpense(): DataResult<Double>
    suspend fun refundMoneyObjSpendById(idObjSpend: String, TienTieu: Double): DataResult<Int>
    suspend fun updateObjSpendById(id: String, newName: String, newMoneyBanDau: Double, newImgObjSpend: Int): DataResult<Int>
}