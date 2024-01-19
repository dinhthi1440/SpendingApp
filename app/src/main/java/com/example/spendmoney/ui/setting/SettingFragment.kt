package com.example.spendmoney.ui.setting


import android.app.Dialog
import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.databinding.FragmentSettingBinding
import com.example.spendmoney.extension.changeName
import com.example.spendmoney.extension.getUserName
import com.example.spendmoney.extension.saveUserName
import org.koin.android.ext.android.get

class SettingFragment :BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {
    override val viewModel: SettingViewModel
        get() = ViewModelProvider(this)[SettingViewModel::class.java]

    private val sharedPreferences = get<SharedPreferences>()
    override fun destroy() {}

    override fun initData() {}

    override fun handleEvent() {
        val dialog = context?.let { it1 -> Dialog(it1) }
        binding.apply {
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            changeNameUser.setOnClickListener {
                dialog?.changeName(sharedPreferences.getUserName().toString()){ newUserName ->
                    sharedPreferences.saveUserName(newUserName)
                }
            }
            Objspending.setOnClickListener {
                findNavController().navigate(R.id.action_settingFragment_to_managementExpenseFragment)
            }
            organizingExpense.setOnClickListener {
                findNavController().navigate(R.id.action_settingFragment_to_organizingExpenseFragment)
            }
        }
    }

    override fun bindData() {}

}