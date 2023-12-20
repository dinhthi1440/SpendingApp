package com.example.spendmoney.ui.spendInput

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.models.ObjSpend
import io.reactivex.rxjava3.core.Single

class InputSpendViewModel(private val objSpend: IObjSpendRepos):BaseViewModel() {

    private val _getObjSpend = MutableLiveData<ObjSpend>()
    val getObjSpend: LiveData<ObjSpend> = _getObjSpend

    private val _statusUpDate = MutableLiveData<String>()
    val statusUpdate: LiveData<String> = _statusUpDate

    fun getObjSpendById(id: String){
        executeTask(
            request = {
                objSpend.getObjSpendById(id)
            },
            onSuccess = {
                _getObjSpend.value = it
                Log.e("TAG", "getObjSpendById: Lấy dữ liệu 1 obj thành công", )
            },
            onError = {
                onErrorProcess(it)
            }
        )
    }
    fun updateMoneyObjSpendById(idObj: String, money: Double){
        executeTask(
            request = {
                objSpend.updateMoneyObjSpendById(idObj, money)
            },
            onSuccess = {
                _statusUpDate.value = it.toString()
                Log.e("TAG", "getObjSpendById: Lấy dữ liệu 1 obj thành công", )
            },
            onError = {
                onErrorProcess(it)
            }
        )
    }
}