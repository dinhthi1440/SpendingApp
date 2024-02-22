package com.example.spendmoney.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.historyspend.HistorySpendRepos
import com.example.spendmoney.data.repository.historyspend.IHistorySpendRepos
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.models.HistorySpend
import com.example.spendmoney.models.ObjSpend

class HomeViewModel(private val objSpendRepos: IObjSpendRepos,
                    private val historySpendRepos: IHistorySpendRepos):BaseViewModel() {

    private val _totalMoney = MutableLiveData<Double>()
    val totalMoney: LiveData<Double> = _totalMoney

    private val _totalMoneyExpense = MutableLiveData<Double>()
    val totalMoneyExpense: LiveData<Double> = _totalMoneyExpense

    private val _spendByMonth = MutableLiveData<List<HistorySpend>>()
    val spendByMonth: LiveData<List<HistorySpend>> get() = _spendByMonth

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
    fun getTotalMoney(){
        executeTask(
            request = {
                objSpendRepos.getTotalMoney()
            },
            onSuccess = {
                _totalMoney.value = it
                Log.e("<TAG", "getTotalMoney: Lấy thành công tổng tiền", )
            },
            onError = {onErrorProcess(it)}
        )
    }

    fun getTotalMoneyExpense(){
        executeTask(
            request = {
                objSpendRepos.getTotalMoneyExpense()
            },
            onSuccess = {
                _totalMoneyExpense.value = it
                Log.e("<TAG", "getTotalMoney: Lấy thành công tổng tiền", )
            },
            onError = {onErrorProcess(it)}
        )
    }

    fun getSpendByDay(month: String, year: String) {
        executeTask(
            request = {historySpendRepos.getSpendByMonthYear(month, year)},
            onSuccess = {
                _spendByMonth.value = it
            },
            onError = { onErrorProcess(it)}
        )
    }
}