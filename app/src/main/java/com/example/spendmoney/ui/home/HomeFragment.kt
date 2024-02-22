package com.example.spendmoney.ui.home

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.databinding.FragmentHomeBinding
import com.example.spendmoney.di.fromJson
import com.example.spendmoney.extension.*
import com.example.spendmoney.models.HistorySpend
import com.example.spendmoney.models.ObjSpend
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {



    override val viewModel by viewModel<HomeViewModel>()
    private val sharedPreferences = get<SharedPreferences>()
    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.imgAvt.setImageURI(it)
        Log.e("TAG", ": uri khi thay là :$it", )
        sharedPreferences.saveUriAvatar(it.toString())
    }
    lateinit var barChart: BarChart

    override fun destroy() {

    }

    override fun initData() {

    }

    override fun handleEvent() {
        binding.layoutSpending.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_objSpendFragment)
        }
        binding.spendTracking.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_showHistoryFragment)
        }
        binding.imgSetting.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
        }
        binding.transactionStatistics.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_statisticalExpenseFragment)
        }

        binding.imgAvt.setOnClickListener {
            contract.launch("image/*")
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bindData() {
        if(sharedPreferences.getUriAvatar() != ""){
            val uriAvatar = Uri.parse(sharedPreferences.getUriAvatar())
            uriAvatar.let {
                binding.imgAvt.setImageURI(it)
            }
            Log.e("TAG", "bindData: uri của avatar là :$uriAvatar", )
        }
        binding.userName.text = sharedPreferences.getUserName()
        if(sharedPreferences.getStatusUserFirstTime() == ""){
            addObjSpendOnce()
        }
        viewModel.getTotalMoney()
        viewModel.getTotalMoneyExpense()
        viewModel.totalMoney.observe(viewLifecycleOwner) {
            viewModel.totalMoneyExpense.observe(viewLifecycleOwner){
                val moneyTotalRemaning = viewModel.totalMoney.value.toString().toDouble() - viewModel.totalMoneyExpense
                    .value.toString().toDouble()
                Log.e("sdfs", "bindData: hớ hớ hớ $moneyTotalRemaning", )
                binding.txtMoneyConlai.text = decimalFormat.format(moneyTotalRemaning).toString() + "đ"
                binding.txtMoneyDatieu.text = "-"+ decimalFormat.format(viewModel.totalMoneyExpense.value).toString() + "đ"
            }
        }
        bindDataBarChart(String.format("%02d", LocalDate.now().monthValue), LocalDate.now().year.toString())

    }
    private fun convertToDailyTotalSpend(historySpendList: List<HistorySpend>): Map<String, Double> {
        val dailyTotalSpend = mutableMapOf<String, Double>()
        for (spend in historySpendList) {
            if (dailyTotalSpend.containsKey(spend.DaySpend)) {
                dailyTotalSpend[spend.DaySpend.substring(0,2)] = dailyTotalSpend[spend.DaySpend.substring(0,2)]!! + spend.Money
            } else {
                dailyTotalSpend[spend.DaySpend.substring(0,2)] = spend.Money
            }
        }
        return dailyTotalSpend
    }

    private fun bindDataBarChart(month: String, year: String){
        binding.textViewInforTimeNow.text = "Tháng ${LocalDate.now().monthValue} năm ${LocalDate.now().year}"
        val list = mutableListOf<BarEntry>()
        val columnNames = mutableListOf<String>()
        var index = -1
        viewModel.getSpendByDay(month, year)
        viewModel.spendByMonth.observe(viewLifecycleOwner){
            val dailyTotalSpend = convertToDailyTotalSpend(it)
            if (dailyTotalSpend.isEmpty()) {
                binding.noDataTextView.visibility = View.VISIBLE
                binding.barChart.visibility = View.GONE
                return@observe
            } else {
                binding.noDataTextView.visibility = View.GONE
                binding.barChart.visibility = View.VISIBLE

                val sortedSpend = dailyTotalSpend.entries.sortedBy { it.key }
                sortedSpend.forEach{ (day, value) ->
                    index +=1
                    list.add(BarEntry(index.toFloat(), value.toFloat()))
                    columnNames.add(day)
                }
                if (list.isNotEmpty() && columnNames.isNotEmpty()) {
                    val barDataSet = BarDataSet(list, "Tổng chi tiêu theo ngày (VNĐ)")
                    barDataSet.setColor(ColorTemplate.rgb("#2aff00"), 255)
                    barDataSet.valueTextColor = Color.BLACK
                    val barData = BarData(barDataSet)
                    binding.barChart.apply {
                        setFitBars(true)
                        data = barData

                        barData.setDrawValues(false)
                        description.isEnabled = false
                        xAxis.apply {
                            valueFormatter = IndexAxisValueFormatter(columnNames)
                            position = XAxis.XAxisPosition.BOTTOM
                            setDrawGridLines(false)
                            granularity = 1f
                        }
                        setTouchEnabled(false)
                        invalidate()
                    }
                }
            }
        }
    }

    private fun addObjSpendOnce(){
            val money = sharedPreferences.getMoney().toString().toDouble()
            val objSpends = listOf(
                ObjSpend("1", "Nhà cửa", money*0.35, 0.0, R.drawable.object_spend_2),
                ObjSpend("2", "Điện", money*0.1, 0.0,R.drawable.object_spend_7),
                ObjSpend("3", "Nước", money*0.06, 0.0, R.drawable.object_spend_6),
                ObjSpend("4", "Ăn uống", money*0.3, 0.0, R.drawable.object_spend_1),
                ObjSpend("5", "Quần áo", money*0.15, 0.0, R.drawable.object_spend_4),
                ObjSpend("6", "Thuốc", money*0.04, 0.0, R.drawable.object_spend_3)
            )
            for (item in objSpends) {
                viewModel.insertObjSpend(item)
            }
            sharedPreferences.saveStatusUserFirstTime("false")
    }

}