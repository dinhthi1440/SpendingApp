package com.example.spendmoney.ui.onboarding.fragment

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.spendmoney.R
import com.example.spendmoney.databinding.FragmentFirstIndicatorInputNameBinding
import com.example.spendmoney.extension.saveUserName
import org.koin.android.ext.android.get

class FirstIndicatorInputNameFragment : Fragment() {

    private val sharedPreferences = get<SharedPreferences>()
    private val binding by lazy{FragmentFirstIndicatorInputNameBinding.inflate(layoutInflater)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onPause() {
        sharedPreferences.saveUserName(binding.newName.text.toString())
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard(view)
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun hideKeyboard(view: View) {
        view.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val imm = requireActivity().getSystemService(InputMethodManager::class.java)
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                sharedPreferences.saveUserName(binding.newName.text.toString())
                return@setOnTouchListener true
            }
            false
        }
    }
}



