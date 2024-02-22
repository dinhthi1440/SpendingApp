package com.example.spendmoney.ui.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spendmoney.ui.onboarding.fragment.FirstIndicatorInputNameFragment
import com.example.spendmoney.ui.onboarding.fragment.SecondIndicatorExpenseFragment
import com.example.spendmoney.ui.onboarding.fragment.ThirtIndicatorInstructionFragment

class ViewPagerAdapter(fragmentManager: FragmentManager):
    FragmentStatePagerAdapter(fragmentManager) {
   /* override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                FirstIndicatorInputNameFragment()
            }
            1 -> {
                SecondIndicatorExpenseFragment()
            }
            2 -> {
                ThirtIndicatorInstructionFragment()
            }
            else -> {Fragment()}
        }
    }*/

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                FirstIndicatorInputNameFragment()
            }
            1 -> {
                SecondIndicatorExpenseFragment()
            }
            2 -> {
                ThirtIndicatorInstructionFragment()
            }
            else -> {Fragment()}
        }
    }

}