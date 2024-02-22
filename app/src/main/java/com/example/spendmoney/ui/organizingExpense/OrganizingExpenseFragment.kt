package com.example.spendmoney.ui.organizingExpense

import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.databinding.FragmentOrganizingExpenseBinding
import com.example.spendmoney.extension.editObjSpend
import com.example.spendmoney.models.ObjSpend
import com.example.spendmoney.ui.objspend.ListAdapterObjSpend
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrganizingExpenseFragment : BaseFragment<FragmentOrganizingExpenseBinding>(FragmentOrganizingExpenseBinding::inflate) {
    override val viewModel by viewModel<OrganizingExpenseViewModel>()

    private val objSpendAdapter by lazy{
        ListAdapterObjSpend(::onClick,::onClickEdit,  true)
    }
    private fun onClickEdit(objSpend: ObjSpend) {
        val dialog = context?.let { it1 -> Dialog(it1) }
        dialog?.editObjSpend(objSpend){
            Log.e("Organizing Expense", "after edit object:${it.MoneyBanDau} ", )
            viewModel.putObjSpend(it.id, it.NameObjSpend, it.MoneyBanDau, it.ImgObjSpend!!)
            viewModel.getStatusPut.observe(viewLifecycleOwner){
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.ObjSpendAdapter.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_right_to_left)
                    binding.ObjSpendAdapter.adapter = objSpendAdapter
                    binding.ObjSpendAdapter.layoutManager = LinearLayoutManager(context)
                    Log.e("Danh sách bên fragment", "getAllObjSpend: ${viewModel.getAllObjSpend.value?.get(3)?.NameObjSpend} ", )
                    viewModel.getAllObjSpend.observe(viewLifecycleOwner){ list ->
                        Log.e("Danh sách bên observe", "getAllObjSpend: ${list.get(3)?.NameObjSpend} ", )
                        objSpendAdapter.submitList(list)
                    }
                },1750 )
            }

        }
    }
    private fun onClick(objSpend: ObjSpend) {}


    override fun destroy() {}

    override fun initData() {}

    override fun handleEvent() {
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }
    }

    override fun bindData() {

        bindAdapter()
    }

    private fun bindAdapter(){
        binding.ObjSpendAdapter.adapter = objSpendAdapter
        binding.ObjSpendAdapter.layoutManager = LinearLayoutManager(context)
        viewModel.getAllObjSpend()
        viewModel.getAllObjSpend.observe(viewLifecycleOwner) {
            objSpendAdapter.submitList(it)
        }
    }

}