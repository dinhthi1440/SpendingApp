package com.example.spendmoney.extension

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
import com.example.spendmoney.databinding.BottomSheetLayoutBinding
import com.example.spendmoney.databinding.DlAnimationOkBinding
import com.example.spendmoney.models.ObjSpend
import com.example.spendmoney.models.TypeSpending
import com.example.spendmoney.ui.spend.ListAdapterSpending

fun Dialog.start(stopFlag: Boolean = false) {
    val marginY = -170
    //val binding = DlLoadingBinding.inflate(layoutInflater)
    //setContentView(binding.root)
    /*window?.apply {
        setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        attributes.apply {
            y = marginY
            gravity = Gravity.CENTER
        }
    }*/
    show()
    setCancelable(stopFlag)
}


private fun onClickDialog(typeSpending: TypeSpending) {

}
fun Dialog.ChangeObjSpend(){
    val spendingAdapter by lazy {
        ListAdapterSpending(::onClickDialog)
    }
    val binding = BottomSheetLayoutBinding.inflate(layoutInflater)
    setContentView(binding.root)
    show()
    window?.apply {
        setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        attributes.apply {
            windowAnimations = R.style.DialogAnimation

        }
        setGravity(Gravity.BOTTOM)
    }
    val v1 = TypeSpending("1", "Nhà cửa", "sfffff")
    val v2 = TypeSpending("2", "Thuốc", "sfffff")
    val v3 = TypeSpending("3", "Ăn uống", "sfffff")
    val v5 = TypeSpending("1", "Nhà cửa", "sfffff")
    val v4 = TypeSpending("2", "Thuốc", "sfffff")
    val v6= TypeSpending("3", "Ăn uống", "sfffff")
    val v7 = TypeSpending("1", "Nhà cửa", "sfffff")
    val v8 = TypeSpending("2", "Thuốc", "sfffff")
    val v9 = TypeSpending("3", "Ăn uống", "sfffff")
    val v10 = TypeSpending("1", "Nhà cửa", "sfffff")
    val v12 = TypeSpending("2", "Thuốc", "sfffff")
    val v13 = TypeSpending("3", "Ăn uống", "sfffff")
    val typeSpeding= listOf(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v12, v13)
    binding.ObjSpendRecyclerview.layoutManager = GridLayoutManager(context,4, LinearLayoutManager.VERTICAL, false)
    spendingAdapter.submitList(typeSpeding)
    binding.ObjSpendRecyclerview.adapter = spendingAdapter
    binding.textView2.setOnClickListener {
        this.dismiss()
    }
}

fun Dialog.openDlOk(stopFlag: Boolean = false) {
    val binding = DlAnimationOkBinding.inflate(layoutInflater)
    setContentView(binding.root)
    window?.apply {
        setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        attributes.apply {
            gravity = Gravity.CENTER
        }
    }
    Handler(Looper.getMainLooper()).postDelayed({
        this.dismiss()
    },1700 )
    binding.lottie.animate().setDuration(1700).setStartDelay(0)

    setCancelable(stopFlag)
    show()
}


