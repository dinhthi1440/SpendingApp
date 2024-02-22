package com.example.spendmoney.ui.onboarding.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spendmoney.R
import com.example.spendmoney.databinding.FragmentThirtIndicatorInstructionBinding
import com.example.spendmoney.ui.main.MainActivity
import com.example.spendmoney.ui.onboarding.OnBoardingActivity

class ThirtIndicatorInstructionFragment : Fragment() {
    private val binding by lazy { FragmentThirtIndicatorInstructionBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_thirt_indicator_instruction,container, false)
    }

}