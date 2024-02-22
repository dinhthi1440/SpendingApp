package com.example.spendmoney.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.spendmoney.extension.start
import java.text.DecimalFormat
import java.text.ParseException

abstract class BaseFragment<VB: ViewBinding>(
    private val BindingInflater: (LayoutInflater) -> VB
): Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding as VB
    protected abstract val viewModel: BaseViewModel
    protected val decimalFormat = DecimalFormat("#,###.###")
    private val dialog by lazy { context?.let { Dialog(it) } }


    protected fun addTextWatcher(editText: EditText) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BindingInflater(layoutInflater)


        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                dialog?.start(false)
            } else {
                dialog?.dismiss()
            }
        }
        view.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val imm = requireActivity().getSystemService(InputMethodManager::class.java)
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                return@setOnTouchListener true
            }
            false
        }
        handleEvent()
        bindData()
        destroy()
    }

    abstract fun destroy()
    abstract fun initData()
    abstract fun handleEvent()
    abstract fun bindData()

    override fun onDestroyView() {
        super.onDestroyView()
        dialog?.dismiss()
    }
}