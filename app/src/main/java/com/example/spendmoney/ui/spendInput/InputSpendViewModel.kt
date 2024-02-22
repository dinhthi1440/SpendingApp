package com.example.spendmoney.ui.spendInput

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.historyspend.IHistorySpendRepos
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.models.HistorySpend
import com.example.spendmoney.models.ObjSpend
import io.reactivex.rxjava3.core.Single

class InputSpendViewModel(private val objSpend: IObjSpendRepos,
                          private val historySpend: IHistorySpendRepos):BaseViewModel() {

    private val _getObjSpend = MutableLiveData<ObjSpend>()
    val getObjSpend: LiveData<ObjSpend> = _getObjSpend

    private val _statusUpDate = MutableLiveData<String>()
    val statusUpdate: LiveData<String> = _statusUpDate

    private val _statusDeduction = MutableLiveData<Long>()
    val statusDeduction: LiveData<Long> = _statusDeduction

    private val _getAllObjSpend = MutableLiveData<List<ObjSpend>>()
    val getAllObjSpend: LiveData<List<ObjSpend>> = _getAllObjSpend

    fun getObjSpendById(id: String){
        executeTask(
            request = {
                objSpend.getObjSpendById(id)
            },
            onSuccess = {
                _getObjSpend.value = it
                getAllObjSpend()
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
    fun deduction(bill: HistorySpend){
        executeTask(
            request = {historySpend.insertHistory(bill)},
            onSuccess = {
                _statusDeduction.value = it
                Log.e("InputSpendViewModel", "deduction: Đã trừ tiền thành công", )
            },
            onError = {onErrorProcess(it)}
        )

    }


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
}