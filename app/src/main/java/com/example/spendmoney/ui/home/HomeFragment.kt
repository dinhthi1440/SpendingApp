package com.example.spendmoney.ui.home

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.databinding.FragmentHomeBinding
import com.example.spendmoney.models.ObjSpend
import com.example.spendmoney.ui.history.ShowHistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    override val viewModel by viewModel<HomeViewModel>()
    /*override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]*/

    override fun destroy() {

    }

    override fun initData() {

    }

    override fun handleEvent() {
        binding.layoutSpending.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_objSpendFragment)
        }
        binding.spendTracking.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_showHistoryFragment)
        }
    }

    override fun bindData() {
        Log.e("TAG", "bindData: đã tới homefragment", )
        val v1 = ObjSpend("1", "Nhà cửa", 1500000.0, 0.0, "")
        val v2 = ObjSpend("2", "Điện", 70000.0, 20000.0,"")
        val v3 = ObjSpend("3", "Nước", 170000.0, 70000.0, "")
        val v4 = ObjSpend("4", "Ăn uống", 1200000.0, 500000.0, "")
        val v5 = ObjSpend("5", "Quần áo", 1000000.0, 750000.0, "")
        val objSpends = listOf(v1, v2, v3, v4, v5)
        for (item in objSpends){
            Log.e("TAG", "bindData: đã tới thêm item", )
            viewModel.insertObjSpend(item)
        }
    }
}