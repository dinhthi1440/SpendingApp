package com.example.spendmoney.ui.spendInput

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.databinding.FragmentInputSpendBinding
import com.example.spendmoney.extension.changeObjSpend
import com.example.spendmoney.extension.openDlOk
import com.example.spendmoney.models.HistorySpend
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.util.*


class InputSpendFragment : BaseFragment<FragmentInputSpendBinding>(FragmentInputSpendBinding::inflate) {

    private val args: InputSpendFragmentArgs by navArgs()
    override val viewModel by viewModel<InputSpendViewModel>()

    override fun destroy() {

    }

    override fun initData() {
        viewModel.getObjSpendById(args.id)
    }

    override fun handleEvent() {
        binding.imgCalendarDateSpend.setOnClickListener {
            val cal = Calendar.getInstance()
            val dpd = context?.let { it1 ->
                DatePickerDialog(
                    it1, { _, year, month, myDay ->
                        val formattedMonth = String.format("%02d", month + 1)
                        val formattedDay = String.format("%02d", myDay)
                        binding.edtChooseDay.setText("$formattedDay / $formattedMonth / $year")
                    }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
                )
            }
            dpd?.show()
        }
        val dialog = context?.let { it1 -> Dialog(it1) }
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.imgChangeObj.setOnClickListener {
            viewModel.getAllObjSpend()
            viewModel.getAllObjSpend.value?.let { it1 -> dialog?.changeObjSpend(it1){ obj ->
                binding.apply {
                    txtNameObjSpend.text = obj.NameObjSpend
                    txtMoneyConlai.text = "Còn lại: " + obj.moneyConLai.toString()
                    txtMoneyDatieu.text = "Đã tiêu: " + obj.MoneyDaTieu.toString()
                    txtTotalMoney.text = obj.MoneyBanDau.toString()
                    progressObjSpend.progress = obj.numberOfProgress.toInt()
                    txtNumberOfProgressObjSpend.text = obj.numberOfProgress.toString() +"%"
                }
            } }
        }
        binding.cardviewContinue.setOnClickListener {
            val numberEmpty = binding.edtNumberMoney.text.toString()
            if(numberEmpty.isNotEmpty()){
                val aBill = HistorySpend(
                    LocalDateTime.now().toString(),
                    binding.txtNameObjSpend.text.toString(),
                    binding.edtNumberMoney.text.toString().toDouble(),
                    binding.edtChooseDay.text.toString(),
                    "",
                    binding.edtContentSpend.text.toString()
                )
                viewModel.updateMoneyObjSpendById(args.id, numberEmpty.toDouble())
                viewModel.statusUpdate.observe(viewLifecycleOwner){
                    viewModel.deduction(aBill)
                    viewModel.getObjSpendById(args.id)
                    bindMoney()
                    dialog?.openDlOk()
                }
            }
        }
    }
    override fun bindData() {
        bindMoney()
    }
    private fun bindMoney(){
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