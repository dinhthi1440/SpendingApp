package com.example.spendmoney.ui.main

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseActivity
import com.example.spendmoney.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    private val navController by lazy { findNavController(R.id.nav_host_fragment_activity_main) }
    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {
        Log.e("TAG", "bindData: chạy", )
        setupWithNavController(binding.navView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                else -> {
                    setNavigationVisible()
                }
            }
        }
    }

    private fun setNavigationVisible(isVisible: Boolean = false) {
        try {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.navView.isVisible = isVisible
            },500)
        }catch (e: Exception){
            binding.navView.isVisible = isVisible
        }
    }


}