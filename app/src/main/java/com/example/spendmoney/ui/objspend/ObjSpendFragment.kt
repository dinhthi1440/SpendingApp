package com.example.spendmoney.ui.objspend

import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.databinding.FragmentObjSpendBinding
import com.example.spendmoney.models.ObjSpend
import org.koin.androidx.viewmodel.ext.android.viewModel

class ObjSpendFragment : BaseFragment<FragmentObjSpendBinding>(FragmentObjSpendBinding::inflate) {

    private val objSpendAdapter by lazy{
        ListAdapterObjSpend(::onClick, ::onClickEdit, false)
    }


    override val viewModel by viewModel<ObjSpendViewModel>()

    override fun destroy() {

    }

    override fun initData() {

    }

    override fun handleEvent() {
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }
    }

    override fun bindData() {
        binding.ObjSpendAdapter.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_right_to_left)
        binding.ObjSpendAdapter.adapter = objSpendAdapter
        binding.ObjSpendAdapter.layoutManager = LinearLayoutManager(context)
        viewModel.getAllObjSpend()
        viewModel.getAllObjSpend.observe(viewLifecycleOwner) {
            objSpendAdapter.submitList(it)
        }
    }

    private fun onClick(objSpend: ObjSpend) {
        val action =
            ObjSpendFragmentDirections.actionObjSpendFragmentToInputSpendFragment(objSpend.id)
        findNavController().navigate(action)

    }
    private fun onClickEdit(objSpend: ObjSpend) {

    }
}