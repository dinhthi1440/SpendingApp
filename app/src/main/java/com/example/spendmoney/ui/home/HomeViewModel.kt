package com.example.spendmoney.ui.home

import android.util.Log
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.models.ObjSpend

class HomeViewModel(private val objSpendRepos: IObjSpendRepos):BaseViewModel() {

    fun insertObjSpend( objSpend: ObjSpend){
        executeTask(
            request = {
                objSpendRepos.insertObjSpend(objSpend)
            },
            onSuccess = {
                Log.e("TAG", "insertObjSpend: đã thêm thành công: \n$objSpend", )
            },
            onError = {
                onErrorProcess(it)
            }
        )
    }
}