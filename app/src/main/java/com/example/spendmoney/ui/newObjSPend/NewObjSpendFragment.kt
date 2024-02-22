package com.example.spendmoney.ui.newObjSPend

import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
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

    private var imgObjSpendValue = 0

    override fun destroy() {

    }

    override fun initData() {

    }

    override fun handleEvent() {
        addTextWatcher(binding.edtNumberMoney)
        binding.constlOk.setOnClickListener {
            if(binding.edtNameObj.text != null && binding.edtNumberMoney.text != null && imgObjSpendValue != 0){
                val newObjSpend =  ObjSpend(LocalDateTime.now().toString(),
                    binding.edtNameObj.text.toString(),
                    decimalFormat.parse(binding.edtNumberMoney.text.toString())?.toDouble() ?: 0.0,
                    0.0,
                    imgObjSpendValue
                )
                viewModel.addNewObjSpend(newObjSpend)
                val dialog = context?.let { it1 -> Dialog(it1) }
                dialog?.openDlOk()
                Handler(Looper.getMainLooper()).postDelayed({
                    findNavController().popBackStack()
                },1800)
            }else{
                Toast.makeText(context, "Hãy chọn đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }


    }

    override fun bindData() {
        var listImgObjSpend = mutableListOf(
            R.drawable.object_spend_1,
            R.drawable.object_spend_2,
            R.drawable.object_spend_3,
            R.drawable.object_spend_4,
            R.drawable.object_spend_5,
            R.drawable.object_spend_6,
            R.drawable.object_spend_7
        )
        binding.apply {
            recycleViewItemObjSpend.layoutManager = GridLayoutManager(context,4, LinearLayoutManager.VERTICAL, false)
            recycleViewItemObjSpend.adapter = ListAdapterImgObjSpend(-1, listImgObjSpend, onItemClickListener = {
                imgObjSpendValue = it
            })
        }
    }
}