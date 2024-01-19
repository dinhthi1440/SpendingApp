package com.example.spendmoney.ui.organizingExpense

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.models.ObjSpend

class OrganizingExpenseViewModel(private val objSpendRepos: IObjSpendRepos):BaseViewModel() {
    private val _getAllObjSpend = MutableLiveData<List<ObjSpend>>()
    val getAllObjSpend: LiveData<List<ObjSpend>> = _getAllObjSpend

    fun getAllObjSpend() {
        Log.e("TAG", "getAllObjSpend: chạy tới viewmodel",)
        executeTask(
            request = { objSpendRepos.getAllObjSpend() },
            onSuccess = {
                _getAllObjSpend.value = it
                _messageError.value = it.toString()
                Log.e("TAG", "getAllObjSpend: Thânhf công lấy dữ liệu",)
            },
            onError = {
                onErrorProcess(it)
            }
        )
    }
}