package com.example.spendmoney.ui.managementExpense

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.models.ObjSpend

class ManagementExpenseViewModel(private val objSpend: IObjSpendRepos): BaseViewModel() {

    private val _getAllObjSpend = MutableLiveData<List<ObjSpend>>()
    val getAllObjSpend: LiveData<List<ObjSpend>> = _getAllObjSpend

    private val _statusDelete = MutableLiveData<Int>()
    val statusDelete: LiveData<Int> = _statusDelete

    fun getAllObjSpend(){
        Log.e("TAG", "getAllObjSpend: chạy tới viewmodel", )
        executeTask(
            request = {objSpend.getAllObjSpend() },
            onSuccess = {
                _getAllObjSpend.value = it
                _messageError.value =  it.toString()
                Log.e("TAG", "getAllObjSpend: Thânhf công lấy dữ liệu", )
            },
            onError = {
                onErrorProcess(it)
            }
        )
    }

    fun deleteObjSpend(idObj: String){
        executeTask(
            request = {objSpend.deleteObjSpend(idObj)},
            onSuccess = {
                _statusDelete.value = 1
            },
            onError = {_statusDelete.value = 0}
        )
    }
}