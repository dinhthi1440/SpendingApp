package com.example.spendmoney.ui.spendInput

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.databinding.FragmentInputSpendBinding
import com.example.spendmoney.extension.changeObjSpend
import com.example.spendmoney.extension.openDlOk
import com.example.spendmoney.models.HistorySpend
import com.example.spendmoney.models.ObjSpend
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.util.*


class InputSpendFragment : BaseFragment<FragmentInputSpendBinding>(FragmentInputSpendBinding::inflate) {

    private val args: InputSpendFragmentArgs by navArgs()
    private lateinit var currentObjSpend: ObjSpend
    override val viewModel by viewModel<InputSpendViewModel>()

    override fun destroy() {

    }



    override fun initData() {
        viewModel.getObjSpendById(args.id)
    }

    @SuppressLint("SetTextI18n")
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
        addTextWatcher(binding.edtNumberMoney)
        binding.imgChangeObj.setOnClickListener {
            viewModel.getAllObjSpend()
            viewModel.getAllObjSpend.value?.let { it1 -> dialog?.changeObjSpend(it1){ obj ->
                currentObjSpend = obj
                binding.apply {
                    txtNameObjSpend.text = obj.NameObjSpend
                    txtMoneyConlai.text = "Còn lại: " + decimalFormat.format(obj.moneyConLai).toString() + "đ"
                    txtMoneyDatieu.text = "Đã tiêu: " + decimalFormat.format(obj.MoneyDaTieu).toString() + "đ"
                    txtTotalMoney.text = decimalFormat.format(obj.MoneyBanDau).toString() + "đ"
                    imgObjSpendMain.setImageResource(obj.ImgObjSpend!!)
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
                    currentObjSpend.id,
                    currentObjSpend.NameObjSpend,
                    decimalFormat.parse(binding.edtNumberMoney.text.toString())?.toDouble() ?: 0.0,
                    binding.edtChooseDay.text.toString(),
                    currentObjSpend.ImgObjSpend,
                    binding.edtContentSpend.text.toString()
                )
                viewModel.updateMoneyObjSpendById(args.id, aBill.Money)
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
    @SuppressLint("SetTextI18n")
    private fun bindMoney(){
        viewModel.getObjSpend.observe(viewLifecycleOwner) { obj ->
            currentObjSpend = obj
            binding.apply {
                txtNameObjSpend.text = obj.NameObjSpend
                imgObjSpendMain.setImageResource(currentObjSpend.ImgObjSpend!!)
                txtMoneyConlai.text = "Còn lại: " + decimalFormat.format(obj.moneyConLai).toString() +"đ"
                txtMoneyDatieu.text = "Đã tiêu: " + decimalFormat.format(obj.MoneyDaTieu).toString() +"đ"
                txtTotalMoney.text = decimalFormat.format(obj.MoneyBanDau).toString() + "đ"
                progressObjSpend.progress = obj.numberOfProgress.toInt()
                txtNumberOfProgressObjSpend.text = obj.numberOfProgress.toString() +"%"
                Log.e("check tiền ", "handleEvent: ${obj.moneyConLai}", )

            }
        }
    }
}