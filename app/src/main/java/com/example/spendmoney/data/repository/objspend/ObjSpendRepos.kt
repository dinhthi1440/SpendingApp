package com.example.spendmoney.data.repository.objspend

import com.example.spendmoney.base.BaseReponsitory
import com.example.spendmoney.base.DataResult
import com.example.spendmoney.data.datasource.objspend.IObjSpendDataSource
import com.example.spendmoney.models.ObjSpend
import io.reactivex.rxjava3.core.Single

class ObjSpendRepos(private val local: IObjSpendDataSource.Local): BaseReponsitory(), IObjSpendRepos {
    override suspend fun insertObjSpend(objSpend: ObjSpend): DataResult<Long> {
        return getResult { local.insertObjSpend(objSpend) }
    }

    override suspend fun getAllObjSpend(): DataResult<List<ObjSpend>> {
        return getResult { local.getAllObjSpend() }
    }

    override suspend fun getObjSpendById(id: String): DataResult<ObjSpend> {
        return getResult { local.getObjSpendById(id) }
    }

    override suspend fun updateMoneyObjSpendById(idObj: String, TienTieu: Double): DataResult<Int> {
        return getResult { local.updateMoneySpend(idObj, TienTieu) }
    }

    override suspend fun getTotalMoney(): DataResult<Double> {
        return getResult { local.getTotalMoney() }
    }

    override suspend fun deleteObjSpend(idObj: String): DataResult<Int> {
        return getResult { local.deleteObjSpend(idObj) }
    }

    override suspend fun getTotalMoneyExpense(): DataResult<Double> {
        return  getResult { local.getTotalMoneyExpense() }
    }

    override suspend fun refundMoneyObjSpendById(idObjSpend: String, TienTieu: Double): DataResult<Int> {
        return getResult { local.refundMoneyObjSpendById(idObjSpend, TienTieu) }
    }

    override suspend fun updateObjSpendById(
        id: String,
        newName: String,
        newMoneyBanDau: Double,
        newImgObjSpend: Int
    ): DataResult<Int> {
        return getResult { local.updateObjSpendById(id, newName, newMoneyBanDau, newImgObjSpend) }
    }
}