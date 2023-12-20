package com.example.spendmoney.ui.history


import android.util.Log
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.databinding.FragmentShowHistoryBinding
import com.example.spendmoney.models.HistorySpend
import com.example.spendmoney.models.ObjSpend
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowHistoryFragment : BaseFragment<FragmentShowHistoryBinding>(FragmentShowHistoryBinding::inflate) {

    private val HistorySpendAdapter by lazy {
        ListAdapterHistory()
    }


    override val viewModel by viewModel<ShowHistoryViewModel>()

    override fun destroy() {

    }

    override fun initData() {


    }

    override fun handleEvent() {
        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_showHistoryFragment_to_homeFragment)
        }
    }

    override fun bindData() {
        Log.e("TAG", "bindData: lỗi 1", )
        binding.HistoryRecyclerview.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_right_to_left)
        binding.HistoryRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.HistoryRecyclerview.adapter = HistorySpendAdapter
        /*viewModel.getAllHistorySpend()
        viewModel.listHistorySpend.observe(viewLifecycleOwner){
            HistorySpendAdapter.submitList(it)
            Log.e("TAG", "bindData: lỗi 2", )
        }*/
    }

}