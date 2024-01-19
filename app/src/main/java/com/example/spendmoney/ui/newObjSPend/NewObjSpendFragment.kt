package com.example.spendmoney.ui.newObjSPend

import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.databinding.FragmentNewObjSpendBinding
import com.example.spendmoney.extension.changeName
import com.example.spendmoney.extension.openDlOk
import com.example.spendmoney.models.ObjSpend
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime

class NewObjSpendFragment:BaseFragment<FragmentNewObjSpendBinding>(FragmentNewObjSpendBinding::inflate) {
    override val viewModel by viewModel<NewObjSpendViewModel>()

    override fun destroy() {

    }

    override fun initData() {

    }

    override fun handleEvent() {
        binding.constlOk.setOnClickListener {
            if(binding.edtNameObj.text != null && binding.edtNumberMoney.text != null){
                val newObjSpend =  ObjSpend(LocalDateTime.now().toString(),
                    binding.edtNameObj.text.toString(),
                    binding.edtNumberMoney.text.toString().toDouble(),
                    0.0,
                    ""
                )
                viewModel.addNewObjSpend(newObjSpend)
                val dialog = context?.let { it1 -> Dialog(it1) }
                dialog?.openDlOk()
                Handler(Looper.getMainLooper()).postDelayed({
                    findNavController().popBackStack()
                },1800)
            }else{
                Toast.makeText(context, "Hãy điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }
    }

    override fun bindData() {

    }
}