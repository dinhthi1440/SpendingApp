package com.example.spendmoney.ui.objspend

import android.content.SharedPreferences
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.databinding.FragmentObjSpendBinding
import com.example.spendmoney.models.HistorySpend
import com.example.spendmoney.models.ObjSpend
import org.koin.androidx.viewmodel.ext.android.viewModel

class ObjSpendFragment : BaseFragment<FragmentObjSpendBinding>(FragmentObjSpendBinding::inflate) {

    private val ObjSpendAdapter by lazy{
        ListAdapterObjSpend(::onClick)
    }


    override val viewModel by viewModel<ObjSpendViewModel>()

    override fun destroy() {

    }

    override fun initData() {

    }

    override fun handleEvent() {
        binding.imgBack.setOnClickListener { findNavController().navigate(R.id.action_objSpendFragment_to_homeFragment) }
    }

    override fun bindData() {
        binding.ObjSpendAdapter.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_right_to_left)
        binding.ObjSpendAdapter.adapter = ObjSpendAdapter
        binding.ObjSpendAdapter.layoutManager = LinearLayoutManager(context)
        viewModel.getAllObjSpend()
        viewModel.getAllObjSpend.observe(viewLifecycleOwner) {
            ObjSpendAdapter.submitList(it)
            Log.e("TAG", "bindData: $it", )
        }


    }

    private fun onClick(objSpend: ObjSpend) {
        Toast.makeText(context, "Item lớn", Toast.LENGTH_SHORT).show()
        val action = ObjSpendFragmentDirections.actionObjSpendFragmentToInputSpendFragment(objSpend.id)
        findNavController().navigate(action)
    }
}