package com.example.spendmoney.data.datasource.objspend

import android.util.Log
import com.example.spendmoney.data.local.DataBaseLocal
import com.example.spendmoney.models.ObjSpend
import io.reactivex.rxjava3.core.Single

class ObjSpendLocalDataSource(private val dataBaseLocal: DataBaseLocal): IObjSpendDataSource.Local {
    override suspend fun insertObjSpend(objSpend: ObjSpend): Long {
        return dataBaseLocal.ObjSpendDao.insert(objSpend)
    }

    override suspend fun getAllObjSpend(): List<ObjSpend> {
        return dataBaseLocal.ObjSpendDao.getAll()
    }

    override suspend fun getObjSpendById(id: String): ObjSpend {
        return dataBaseLocal.ObjSpendDao.getObjById(id)
    }

    override suspend fun updateMoneySpend(idObj: String, TienTieu: Double): Int {
        return dataBaseLocal.ObjSpendDao.updateMoneyObjSpendById(idObj, TienTieu)
    }

    override suspend fun getTotalMoney(): Double {
        return dataBaseLocal.ObjSpendDao.getTotalMoney()
    }

    override suspend fun deleteObjSpend(idObj: String): Int {
        return dataBaseLocal.ObjSpendDao.deleteObjSpend(idObj)
    }

    override suspend fun getTotalMoneyExpense(): Double {
        return dataBaseLocal.ObjSpendDao.getTotalMoneyExpense()
    }

    override suspend fun refundMoneyObjSpendById(nameObjSpend: String, TienTieu: Double): Int {
        return dataBaseLocal.ObjSpendDao.refundMoneyObjSpendById(nameObjSpend, TienTieu)
    }
}