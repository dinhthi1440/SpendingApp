package com.example.spendmoney.ui.spendInput

import android.app.Dialog
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.databinding.FragmentInputSpendBinding
import com.example.spendmoney.extension.ChangeObjSpend
import com.example.spendmoney.extension.openDlOk
import org.koin.androidx.viewmodel.ext.android.viewModel


class InputSpendFragment : BaseFragment<FragmentInputSpendBinding>(FragmentInputSpendBinding::inflate) {

    private val args: InputSpendFragmentArgs by navArgs()
    override val viewModel by viewModel<InputSpendViewModel>()

    override fun destroy() {

    }

    override fun initData() {
        viewModel.getObjSpendById(args.id)
    }

    override fun handleEvent() {
        val dialog = context?.let { it1 -> Dialog(it1) }
        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_inputSpendFragment_to_objSpendFragment)
        }
        binding.imgChangeObj.setOnClickListener {
            dialog?.ChangeObjSpend()
        }
        Log.e("TAG", "handleEvent: id là" + args.id)

        binding.cardviewContinue.setOnClickListener {
            val numberEmpty = binding.edtNumberMoney.text.toString()
            if(numberEmpty.isNotEmpty()){
                viewModel.updateMoneyObjSpendById(args.id, numberEmpty.toDouble())
                viewModel.statusUpdate.observe(viewLifecycleOwner){
                    viewModel.getObjSpendById(args.id)
                    BindMoney()
                    dialog?.openDlOk()
                }
            }
        }
    }
    override fun bindData() {
        BindMoney()
    }

    fun BindMoney(){
        viewModel.getObjSpend.observe(viewLifecycleOwner) { obj ->
            binding.apply {
                txtNameObjSpend.text = obj.NameObjSpend
                txtMoneyConlai.text = "Còn lại: " + obj.moneyConLai.toString()
                txtMoneyDatieu.text = "Đã tiêu: " + obj.MoneyDaTieu.toString()
                txtTotalMoney.text = obj.MoneyBanDau.toString()
                progressObjSpend.progress = obj.numberOfProgress.toInt()
                txtNumberOfProgressObjSpend.text = obj.numberOfProgress.toString() +"%"
            }
        }
    }

}