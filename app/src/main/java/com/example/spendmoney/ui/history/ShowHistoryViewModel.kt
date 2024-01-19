package com.example.spendmoney.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.historyspend.IHistorySpendRepos
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.models.HistorySpend

class ShowHistoryViewModel(private val historySpendRepos: IHistorySpendRepos,
                           private val objspendRepos: IObjSpendRepos):BaseViewModel() {
    private val _listHistorySpend = MutableLiveData<List<HistorySpend>>()
    val listHistorySpend: LiveData<List<HistorySpend>> = _listHistorySpend
    fun getAllHistorySpend(){
        Log.e("TAG", "getAllHistorySpend: chạy tới viewmodel", )
        executeTask(
            request = {
                historySpendRepos.getAllHistory()
            },
            onSuccess = {
                _listHistorySpend.value = it
                Log.e("TAG", "getAllHistorySpend: Lấy danh sách history thành công", )
            },
            onError = {onErrorProcess(it)}
        )
    }

    fun deleteHistorySpend(idHistorySpend: String){
        executeTask(
            request = {historySpendRepos.deleteHistorySpend(idHistorySpend)},
            onSuccess = {

            },
            onError = {
                onErrorProcess(it)
            }
        )
    }

    fun refundMoneyObjSpendById(nameObjSpend: String, TienTieu: Double){
        executeTask(
            request = {objspendRepos.refundMoneyObjSpendById(nameObjSpend, TienTieu)},
            onSuccess = {},
            onError = {onErrorProcess(it)}
        )
    }
}