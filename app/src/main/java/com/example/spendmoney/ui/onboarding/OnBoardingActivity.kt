package com.example.spendmoney.ui.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.spendmoney.databinding.ActivityOnBoardingBinding
import com.example.spendmoney.extension.getStatusUserFirstTime
import com.example.spendmoney.ui.main.MainActivity
import com.example.spendmoney.ui.onboarding.adapter.ViewPagerAdapter
import org.koin.android.ext.android.get

class OnBoardingActivity : AppCompatActivity() {
    private val binding by lazy { ActivityOnBoardingBinding.inflate(layoutInflater)}
    private val sharedPreferences = get<SharedPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(sharedPreferences.getStatusUserFirstTime() =="false"){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("key", "value")
            startActivity(intent)
            finish()
        }
        val adapter = ViewPagerAdapter(supportFragmentManager)
        binding.apply {
            viewPager.adapter = adapter
            indicator.setViewPager(viewPager)
            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    when(position){
                        0 ->{
                            btnContinue.visibility = View.INVISIBLE
                            btnBack.visibility = View.INVISIBLE
                            btnNext.visibility = View.VISIBLE
                        }
                        1 ->{
                            btnContinue.visibility = View.INVISIBLE
                            btnBack.visibility = View.VISIBLE
                            btnNext.visibility = View.VISIBLE
                        }
                        2 -> {
                            btnContinue.visibility = View.VISIBLE
                            btnBack.visibility = View.INVISIBLE
                            btnNext.visibility = View.INVISIBLE
                        }
                    }
                }
                override fun onPageScrollStateChanged(state: Int) {}
            })
            if(viewPager.currentItem < 2){
                btnNext.setOnClickListener { viewPager.currentItem = viewPager.currentItem + 1 }
                btnBack.setOnClickListener { viewPager.currentItem = viewPager.currentItem - 1 }
            }
            Log.e("TAG", "onCreate: thứ tự ban đầu là: ${viewPager.currentItem}", )
        }
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("key", "value")
        binding.btnContinue.setOnClickListener {
            startActivity(intent)
            finish()
        }
    }
}