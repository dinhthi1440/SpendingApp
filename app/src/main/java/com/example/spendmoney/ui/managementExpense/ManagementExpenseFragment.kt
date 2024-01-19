package com.example.spendmoney.ui.managementExpense

import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.databinding.FragmentManagementExpenseBinding
import com.example.spendmoney.extension.openDlOk
import com.example.spendmoney.models.ObjSpend
import com.example.spendmoney.ui.objspend.ListAdapterObjSpend
import com.example.spendmoney.ui.objspend.ObjSpendFragmentDirections
import com.example.spendmoney.ui.spend.ListAdapterSpending
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel


class ManagementExpenseFragment : BaseFragment<FragmentManagementExpenseBinding>(FragmentManagementExpenseBinding::inflate) {
    override val viewModel by viewModel<ManagementExpenseViewModel>()
    private var isVisibility: Boolean = false


    override fun destroy() {

    }

    override fun initData() {
    }

    override fun handleEvent() {
        binding.apply {
            trashCan.setOnClickListener {
                isVisibility = true
                trashCan.visibility = View.INVISIBLE
                txtFinish.visibility = View.VISIBLE
                bindAdapter(true)
            }
            txtFinish.setOnClickListener {
                isVisibility = false
                trashCan.visibility = View.VISIBLE
                txtFinish.visibility = View.INVISIBLE
                bindAdapter(false)
            }
            imgBack.setOnClickListener { findNavController().popBackStack() }
            imgInsertNewObjspend.setOnClickListener { findNavController().navigate(R.id.action_managementExpenseFragment_to_newObjSpendFragment) }
        }

    }

    override fun bindData() {
        viewModel.getAllObjSpend()
        binding.recviewDanhmuc.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_right_to_left)
        binding.recviewDanhmuc.layoutManager = GridLayoutManager(context,4, LinearLayoutManager.VERTICAL, false)
        bindAdapter(false)
    }
    private fun bindAdapter(isVisibility: Boolean){
        val listObjspend by lazy {
            ListAdapterSpendingManagement(::onClick, isVisibility)
        }
        binding.recviewDanhmuc.adapter = listObjspend
        viewModel.getAllObjSpend.observe(viewLifecycleOwner) {
            listObjspend.submitList(it)
        }
    }
    private fun onClick(objSpend: ObjSpend) {
        val dialog = context?.let { it1 -> Dialog(it1) }
        viewModel.apply {
            deleteObjSpend(objSpend.id)
            statusDelete.observe(viewLifecycleOwner){
                if(it == 1) {
                    dialog?.openDlOk()
                    Handler(Looper.getMainLooper()).postDelayed({
                        bindAdapter(true)
                        getAllObjSpend()
                    },1800)
                }
                else Toast.makeText(context, "Xoá thất bại", Toast.LENGTH_SHORT).show()
            }
        }
    }
}