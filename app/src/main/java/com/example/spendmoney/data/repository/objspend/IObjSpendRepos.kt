package com.example.spendmoney.data.repository.objspend

import com.example.spendmoney.base.DataResult
import com.example.spendmoney.models.ObjSpend
import io.reactivex.rxjava3.core.Single

interface IObjSpendRepos {
    suspend fun insertObjSpend(objSpend: ObjSpend): DataResult<Long>
    suspend fun getAllObjSpend(): DataResult<List<ObjSpend>>
    suspend fun getObjSpendById(id: String): DataResult<ObjSpend>
    suspend fun updateMoneyObjSpendById(idObj: String, TienTieu: Double): DataResult<Int>
}