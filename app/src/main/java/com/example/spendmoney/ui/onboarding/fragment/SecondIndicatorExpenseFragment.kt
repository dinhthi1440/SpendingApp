package com.example.spendmoney.ui.onboarding.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
import com.example.spendmoney.data.repository.objspend.IObjSpendRepos
import com.example.spendmoney.databinding.FragmentSecondIndicatorExpenseBinding
import com.example.spendmoney.di.toJsonString
import com.example.spendmoney.extension.saveMoney
import com.example.spendmoney.models.ObjSpend
import com.example.spendmoney.ui.main.MainActivity
import com.example.spendmoney.ui.objspend.ListAdapterObjSpend
import org.koin.android.ext.android.get
import java.text.DecimalFormat
import java.text.ParseException

class SecondIndicatorExpenseFragment() : Fragment() {
    private val sharedPreferences = get<SharedPreferences>()
    private val binding by lazy { FragmentSecondIndicatorExpenseBinding.inflate(layoutInflater) }

    private val objSpendAdapter by lazy{
        ListAdapterObjSpend(::onClick, ::onClickEdit, false)
    }
    val decimalFormat = DecimalFormat("#,###.###")

    private fun addTextWatcher(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val enteredText = p0.toString()
                try {
                    val formattedText = decimalFormat.format(decimalFormat.parse(enteredText))
                    if (formattedText != enteredText) {
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTextWatcher(binding.textInPutMoney)
        binding.btnSuggest.setOnClickListener {
            divideExpense(view)

        }
        hideKeyboard(view)
    }

    private fun divideExpense(view: View){
        var money = 0.0
        if(binding.textInPutMoney.text.toString() != ""){
            money = decimalFormat.parse(binding.textInPutMoney.text.toString())?.toDouble() ?: 0.0
        }
            if(money!=0.0){
                binding.recycleViewItemObjSpend.visibility = View.VISIBLE
                bindAdapter(money)
            }else{
                binding.recycleViewItemObjSpend.visibility = View.INVISIBLE
            }
            val imm = requireActivity().getSystemService(InputMethodManager::class.java)
            imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun hideKeyboard(view: View) {
        view.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val imm = requireActivity().getSystemService(InputMethodManager::class.java)
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                return@setOnTouchListener true
            }
            false
        }
    }

    override fun onPause() {
        var money: Double
        if(binding.textInPutMoney.text.toString() != ""){
            money = decimalFormat.parse(binding.textInPutMoney.text.toString())?.toDouble() ?: 0.0
            Log.e("tag", "onPause: Không trống $money", )
        }else{
            money = 0.0
            Log.e("tag", "onPause: Đang trống $money", )
        }
        sharedPreferences.saveMoney(money.toString())
        super.onPause()
    }

    private fun bindAdapter(money: Double){
        Log.e("TAG", "bindAdapter: $money", )
        val objSpends = listOf(
            ObjSpend("1", "Nhà cửa", money*0.35, 0.0, R.drawable.object_spend_2),
            ObjSpend("2", "Điện", money*0.1, 0.0,R.drawable.object_spend_7),
            ObjSpend("3", "Nước", money*0.06, 0.0, R.drawable.object_spend_6),
            ObjSpend("4", "Ăn uống", money*0.3, 0.0, R.drawable.object_spend_1),
            ObjSpend("5", "Quần áo", money*0.15, 0.0, R.drawable.object_spend_4),
            ObjSpend("6", "Thuốc", money*0.04, 0.0, R.drawable.object_spend_3)
        )
        binding.recycleViewItemObjSpend.layoutManager = LinearLayoutManager(context)
        objSpendAdapter.submitList(objSpends)
        binding.recycleViewItemObjSpend.adapter = objSpendAdapter
    }

    private fun onClick(objSpend: ObjSpend) {

    }
    private fun onClickEdit(objSpend: ObjSpend) {

    }
}