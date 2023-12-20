package com.example.spendmoney.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.historyspend.IHistorySpendRepos
import com.example.spendmoney.models.HistorySpend

class ShowHistoryViewModel(private val historySpendRepos: IHistorySpendRepos):BaseViewModel() {

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
}