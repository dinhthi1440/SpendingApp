package com.example.spendmoney.ui.statisticalExpense

import android.graphics.Color
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.util.getColumnIndex
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.base.BaseViewModel
import com.example.spendmoney.databinding.FragmentStatisticalExpenseBinding
import com.example.spendmoney.models.HistorySpend
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.transition.MaterialSharedAxis.Axis
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticalExpenseFragment: BaseFragment<FragmentStatisticalExpenseBinding>(FragmentStatisticalExpenseBinding::inflate) {
    override val viewModel by viewModel<StatisticalExpenseViewModel>()

    lateinit var barChart: BarChart
    lateinit var pieChart: PieChart

    override fun destroy() {

    }

    override fun initData() {

    }

    override fun handleEvent() {
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun bindData() {
        bindDataBarChart("01")
        bindDataPieChart()
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

    private fun convertToObjectSpend(historySpendList: List<HistorySpend>): Map<String, Double> {
        val objectSpend = mutableMapOf<String, Double>()
        for (spend in historySpendList) {
            if (objectSpend.containsKey(spend.TypeSpend)) {
                objectSpend[spend.TypeSpend] = objectSpend[spend.TypeSpend]!! + spend.Money
            } else {
                objectSpend[spend.TypeSpend] = spend.Money
            }
        }
        return objectSpend
    }

    private fun bindDataBarChart(month: String){
        val list = mutableListOf<BarEntry>()
        val columnNames = mutableListOf<String>()
        var index = -1
        viewModel.getSpendByDay(month)
        viewModel.spendByMonth.observe(viewLifecycleOwner){
            val dailyTotalSpend = convertToDailyTotalSpend(it)
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
                    animateY(2000)
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
    private fun bindDataPieChart(){
        val listPieEntry = mutableListOf<PieEntry>()

        val colors: IntArray = ColorTemplate.MATERIAL_COLORS
        val colorsList: MutableList<Int> = colors.toMutableList()

        viewModel.spendByMonth.observe(viewLifecycleOwner){ list ->
            val objectTotalSpend = convertToObjectSpend(list)
            val totalSpend = objectTotalSpend.values.sum()
            Log.e("statisticalExpense", "bindDataPieChart: $list  và $totalSpend", )
            objectTotalSpend.forEach{
                listPieEntry.add(PieEntry((it.value*100/totalSpend).toFloat(), it.key ))
            }
            val pieDataSet = PieDataSet(listPieEntry, "Tỉ lệ các khoản")
            pieDataSet.colors = colorsList

            pieDataSet.valueTextSize = 15f
            pieDataSet.valueTextColor = Color.BLACK

            val pieData = PieData(pieDataSet)
            binding.pieChart.apply {
                description.isEnabled = false
                data = pieData
                centerText = "Đã tiêu (%)"
                animateY(2000)
            }
        }


    }

}


