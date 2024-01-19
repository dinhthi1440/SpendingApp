package com.example.spendmoney.extension

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
import com.example.spendmoney.databinding.BottomSheetLayoutBinding
import com.example.spendmoney.databinding.DlAnimationOkBinding
import com.example.spendmoney.databinding.DlChooseTypeSortBinding
import com.example.spendmoney.databinding.DlEditObjectSpendBinding
import com.example.spendmoney.databinding.DlUpdateNameUserBinding
import com.example.spendmoney.models.ObjSpend
import com.example.spendmoney.ui.spend.ListAdapterSpending
import java.util.*

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


fun Dialog.changeObjSpend(listObjSpend: List<ObjSpend> ,callback: (ObjSpend) -> Unit){
    val spendingAdapter by lazy {
        ListAdapterSpending(callback)
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
    binding.ObjSpendRecyclerview.layoutManager = GridLayoutManager(context,4, LinearLayoutManager.VERTICAL, false)
    spendingAdapter.submitList(listObjSpend)
    binding.ObjSpendRecyclerview.adapter = spendingAdapter
    binding.txtCancel.setOnClickListener {
        this.dismiss()
    }
    spendingAdapter.onItemClick = {
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
    binding.lottie.animate().setDuration(1700).startDelay = 0

    setCancelable(stopFlag)
    show()
}

fun Dialog.changeName(userName: String, callback: (String) -> Unit) {
    val marginY = -100
    val binding = DlUpdateNameUserBinding.inflate(layoutInflater)
    setContentView(binding.root)
    show()
    window?.apply {
        setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        attributes.apply {
            y = marginY
            gravity = Gravity.CENTER
        }
    }
    val dialog = Dialog(context)
    binding.btnOk.setOnClickListener {
        if(binding.newName.text != null){
            callback(binding.newName.text.toString())
            binding.oldName.text = binding.newName.text
            dialog.openDlOk()
            Handler(Looper.getMainLooper()).postDelayed({
                this.dismiss()
            },1750 )
        }
    }
    binding.oldName.text = userName
    binding.btnCancel.setOnClickListener {
        this.dismiss()
    }
}


fun Dialog.editObjSpend(objSpend: ObjSpend, callback: (ObjSpend) -> Unit) {
    val marginY = -100
    val binding = DlEditObjectSpendBinding.inflate(layoutInflater)
    setContentView(binding.root)
    show()
    window?.apply {
        setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        attributes.apply {
            y = marginY
            gravity = Gravity.CENTER
        }
    }
    val dialog = Dialog(context)
    binding.constlCancel.setOnClickListener { this.dismiss() }
    binding.constlOk.setOnClickListener {
        dialog.openDlOk()
        Handler(Looper.getMainLooper()).postDelayed({
            this.dismiss()
        },1750 )
        callback(ObjSpend(
            objSpend.id, binding.edtNameObj.text.toString(),
            binding.edtNumberMoney.text.toString().toDouble(),
            objSpend.MoneyDaTieu, ""
        ))
    }
    binding.apply {
        edtNameObj.setText(objSpend.NameObjSpend)
        edtNumberMoneySpend.setText(objSpend.MoneyDaTieu.toString())
        edtNumberMoney.setText(objSpend.MoneyBanDau.toString())
    }

}

class SortHistory(
    val id: Int?,
    val nameTypeSort: String ?,
    val dateStart: String ?,
    val dateEnd: String ?
)

@SuppressLint("SetTextI18n")
fun Dialog.sortingHistorySpend(sortHistory: SortHistory, listNameHistorySpend: List<String>, callback: (SortHistory) -> Unit ) {
    val marginY = -100
    val binding = DlChooseTypeSortBinding.inflate(layoutInflater)
    setContentView(binding.root)
    show()
    window?.apply {
        setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        attributes.apply {
            y = marginY
            gravity = Gravity.CENTER
        }
    }

    val dialog = Dialog(context)
    var typeSort = "All"
    var id = 0
    binding.btnOk.setOnClickListener {
        if(binding.txtDateEnd.text != "-- / -- /----" && binding.txtDateStart.text == "-- / -- /----"){
            Toast.makeText(context, "Hãy điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
        }else if(binding.txtDateEnd.text == "-- / -- /----" && binding.txtDateStart.text != "-- / -- /----"){
            Toast.makeText(context, "Hãy điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
        }
        else{
            dialog.openDlOk()
            Handler(Looper.getMainLooper()).postDelayed({
                this.dismiss()
            },1750 )
            callback(SortHistory(id, typeSort, binding.txtDateStart.text.toString(), binding.txtDateEnd.text.toString() ))
        }
    }
    binding.apply {

        imgCalendarDateSpend.setOnClickListener {
            val cal = Calendar.getInstance()
            val dpd = DatePickerDialog(
                context, { _, year, month, myDay ->
                    txtDateStart.text = "" + myDay + " / " + (month+1) + " / " + year
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }
        imgCalendarDateSpend1.setOnClickListener {
            val cal = Calendar.getInstance()
            val dpd = DatePickerDialog(
                context, { _, year, month, myDay ->
                    txtDateEnd.text = "" + myDay + " / " + (month+1) + " / " + year
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }
        val adt = ArrayAdapter(context, R.layout.item_simple_spinner, listNameHistorySpend)
        spinnerObjSpend.adapter = adt
        spinnerObjSpend.onItemSelectedListener = object :AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                typeSort = listNameHistorySpend[p2]
                id = p2
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

        }
        if(sortHistory.id != null){
            txtDateStart.text = sortHistory.dateStart
            txtDateEnd.text = sortHistory.dateEnd
            spinnerObjSpend.setSelection(sortHistory.id)
        }
    }
    binding.btnCancel.setOnClickListener {
        this.dismiss()
    }
}



