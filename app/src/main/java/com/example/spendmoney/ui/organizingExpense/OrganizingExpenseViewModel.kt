package com.example.spendmoney.ui.organizingExpense

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.models.ObjSpend

class OrganizingExpenseViewModel(private val objSpendRepos: IObjSpendRepos):BaseViewModel() {
    private val _getStatusPut = MutableLiveData<Int>()
    val getStatusPut: LiveData<Int> = _getStatusPut

    private val _getAllObjSpend = MutableLiveData<List<ObjSpend>>()
    val getAllObjSpend: LiveData<List<ObjSpend>> = _getAllObjSpend

    fun getAllObjSpend() {
        executeTask(
            request = { objSpendRepos.getAllObjSpend() },
            onSuccess = {
                _getAllObjSpend.value = it
                _messageError.value = it.toString()
                Log.e("TAG", "getAllObjSpend: Thành công lấy dữ liệu",)
                Log.e("Danh sách", "getAllObjSpend: ${it.get(3).NameObjSpend} ", )
            },
            onError = {
                onErrorProcess(it)
            }
        )
    }
    fun putObjSpend(id: String, newName: String, newMoneyBanDau: Double, newImgObjSpend: Int){
        executeTask(
            request = {
                objSpendRepos.updateObjSpendById(id, newName, newMoneyBanDau, newImgObjSpend)
            },
            onSuccess = {
                Log.e("TAG", "putObjSpend: Sửa thành công", )
                _getStatusPut.value = it
                getAllObjSpend()
            },
            onError = {onErrorProcess(it)}
        )
    }
}