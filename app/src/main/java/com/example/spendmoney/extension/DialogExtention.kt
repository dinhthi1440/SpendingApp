package com.example.spendmoney.extension

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
import com.example.spendmoney.databinding.BottomSheetLayoutBinding
import com.example.spendmoney.databinding.DlAnimationOkBinding
import com.example.spendmoney.databinding.DlChangeDateBinding
import com.example.spendmoney.databinding.DlChooseTypeSortBinding
import com.example.spendmoney.databinding.DlEditObjectSpendBinding
import com.example.spendmoney.databinding.DlUpdateNameUserBinding
import com.example.spendmoney.models.ObjSpend
import com.example.spendmoney.ui.newObjSPend.ListAdapterImgObjSpend
import com.example.spendmoney.ui.spend.ListAdapterSpending
import java.text.DecimalFormat
import java.text.ParseException
import java.time.LocalDate
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



fun Dialog.changeDateToSorting(dateTimeCurrent: LocalDate, callback: (LocalDate) -> Unit) {
    val marginY = -100
    val binding = DlChangeDateBinding.inflate(layoutInflater)
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


    var mothSelect: String = dateTimeCurrent.monthValue.toString()
    var yearSelect: String = dateTimeCurrent.year.toString()
    binding.apply {
        val listMonth = listOf("01", "02", "03", "05", "06", "07", "08", "09", "10", "11", "12" )
        val listYear = listOf("2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028",
        "2029", "2030")
        val adtMonth = ArrayAdapter(context, R.layout.item_simple_spinner, listMonth)
        spinnerMonth.adapter = adtMonth
        spinnerMonth.onItemSelectedListener = object :AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                mothSelect = listMonth[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
        }

        val adtYear = ArrayAdapter(context, R.layout.item_simple_spinner, listYear)
        spinnerYear.adapter = adtYear
        spinnerYear.onItemSelectedListener = object :AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                yearSelect = listYear[p2]

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
        }

        spinnerMonth.setSelection(listMonth.indexOf(String.format("%02d", dateTimeCurrent.monthValue)))
        spinnerYear.setSelection(listYear.indexOf(dateTimeCurrent.year.toString()))
    }
    val dialog = Dialog(context)
    binding.btnOk.setOnClickListener {
        dialog.openDlOk()
        Handler(Looper.getMainLooper()).postDelayed({
            val dateChoose = LocalDate.of(yearSelect.toInt(),mothSelect.toInt(), 1)
            callback(dateChoose)
            this.dismiss()
        },1750 )

    }

    binding.btnCancel.setOnClickListener {
        this.dismiss()
    }
}

fun addTextWatcher(editText: EditText) {
    val decimalFormat = DecimalFormat("#,###.######")
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            val enteredText = p0.toString()
            try {
                val formattedText = decimalFormat.format(decimalFormat.parse(enteredText))
                if (formattedText != enteredText && formattedText.length <= 15) {
                    editText.removeTextChangedListener(this)
                    editText.setText(formattedText)
                    editText.setSelection(formattedText.length)
                    editText.addTextChangedListener(this)
                }
            } catch (_: ParseException) {
            }
        }
    })
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
    var imgObjSpendValue = objSpend.ImgObjSpend
    val listImgObjSpend = mutableListOf(
        R.drawable.object_spend_1,
        R.drawable.object_spend_2,
        R.drawable.object_spend_3,
        R.drawable.object_spend_4,
        R.drawable.object_spend_5,
        R.drawable.object_spend_6,
        R.drawable.object_spend_7
    )
    binding.apply {
        recycleViewItemObjSpend.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycleViewItemObjSpend.adapter = ListAdapterImgObjSpend(objSpend.ImgObjSpend!!, listImgObjSpend, onItemClickListener = {
            imgObjSpendValue = it
        })
    }
    val decimalFormat = DecimalFormat("#,###.###")
    val dialog = Dialog(context)
    binding.constlCancel.setOnClickListener { this.dismiss() }
    binding.recycleViewItemObjSpend.adapter
    binding.constlOk.setOnClickListener {
        val moneyRemaining = decimalFormat.parse(binding.edtNumberMoney.text.toString())?.toDouble() ?: 0.0
        if(moneyRemaining < objSpend.MoneyDaTieu!!){
            Toast.makeText(context, "Số tiền trong khoản \n phải lớn hơn số tiền đã tiêu", Toast.LENGTH_SHORT).show()
        }else{
            dialog.openDlOk()
            callback(ObjSpend(
                objSpend.id, binding.edtNameObj.text.toString(),
                moneyRemaining,
                objSpend.MoneyDaTieu, imgObjSpendValue
            ))
            Handler(Looper.getMainLooper()).postDelayed({
                this.dismiss()
            },1750 )
        }
    }
    addTextWatcher(binding.edtNumberMoney)
    addTextWatcher(binding.edtNumberMoneySpend)
    binding.apply {
        edtNameObj.setText(objSpend.NameObjSpend)
        edtNumberMoneySpend.setText(objSpend.MoneyDaTieu?.toInt().toString())
        edtNumberMoney.setText(objSpend.MoneyBanDau.toInt().toString())
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



