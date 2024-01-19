package com.example.spendmoney.ui.newObjSPend

import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.models.ObjSpend

class NewObjSpendViewModel(private val ObjSpend: IObjSpendRepos) : BaseViewModel() {

    fun addNewObjSpend(objSpend: ObjSpend){
        executeTask(
            request = {ObjSpend.insertObjSpend(objSpend)},
            onSuccess = {

            },
            onError = {
                onErrorProcess(it)
            }
        )
    }
}