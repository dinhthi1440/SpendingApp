package com.example.spendmoney.ui.history

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseFragment
import com.example.spendmoney.databinding.FragmentShowHistoryBinding
import com.example.spendmoney.extension.SortHistory
import com.example.spendmoney.extension.openDlOk
import com.example.spendmoney.extension.sortingHistorySpend
import com.example.spendmoney.models.HistorySpend
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class ShowHistoryFragment : BaseFragment<FragmentShowHistoryBinding>(FragmentShowHistoryBinding::inflate) {

    private val historySpendAdapter by lazy {
        ListAdapterHistory(::onClick)
    }
    private val setOfNames = mutableSetOf<String>()

    private var listHistory : List<HistorySpend>? = null
    private var sortHistory = SortHistory(null, null, null, null)
    override val viewModel by viewModel<ShowHistoryViewModel>()

    override fun destroy() {}
    override fun initData() {}

    override fun handleEvent() {
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }
        val dialog = context?.let { it1 -> Dialog(it1) }
        binding.imgSort.setOnClickListener {
            val listNameTypeHistorySpend: List<String> = setOfNames.toList()
            dialog?.sortingHistorySpend(sortHistory,listNameTypeHistorySpend){
                sortHistory = it
                sortingHistory(it)
            }

        }
    }

    private fun sortingHistory(sortHistory: SortHistory) {
            val filteredList = when {
                sortHistory.dateStart != "-- / -- /----" && sortHistory.dateEnd != "-- / -- /----"
                        && sortHistory.nameTypeSort != "Tất cả"-> {
                    listHistory?.filter {
                        it.TypeSpend == sortHistory.nameTypeSort &&
                                parseDate(it.DaySpend) >= parseDate(sortHistory.dateStart ?: "") &&
                                parseDate(it.DaySpend) <= parseDate(sortHistory.dateEnd ?: "")
                    }
                }
                sortHistory.dateStart == "-- / -- /----" && sortHistory.dateEnd == "-- / -- /----"
                        && sortHistory.nameTypeSort == "Tất cả" -> {
                    listHistory
                }
                sortHistory.dateStart != "-- / -- /----" && sortHistory.dateEnd != "-- / -- /----"
                        && sortHistory.nameTypeSort == "Tất cả" -> {
                    listHistory?.filter {
                        parseDate(it.DaySpend) >= parseDate(sortHistory.dateStart ?: "") &&
                                parseDate(it.DaySpend) <= parseDate(sortHistory.dateEnd ?: "")
                    }
                }
                else -> {
                    listHistory?.filter {
                        it.TypeSpend == sortHistory.nameTypeSort
                    }
                }
            }
            if (filteredList.isNullOrEmpty()) {
                Toast.makeText(context, "Không tìm thấy", Toast.LENGTH_SHORT).show()
            } else {
                bindAdapter(filteredList)
                totalAmount(filteredList)
            }
    }

    @SuppressLint("SimpleDateFormat")
    private fun parseDate(dateString: String): Date {
        val format = SimpleDateFormat("dd / MM / yyyy")
        return format.parse(dateString) ?: Date()
    }

    override fun bindData() {
        binding.HistoryRecyclerview.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_right_to_left)
        bindDataAgain()

    }
    private fun onClick(historySpend: HistorySpend){
        viewModel.refundMoneyObjSpendById(historySpend.TypeSpend, historySpend.Money)
        viewModel.deleteHistorySpend(historySpend.id)
        val dialog = context?.let { it1 -> Dialog(it1) }
        dialog?.openDlOk()
        Handler(Looper.getMainLooper()).postDelayed({
            bindDataAgain()
        },1800)
    }

    @SuppressLint("SetTextI18n")
    private fun totalAmount(list: List<HistorySpend>){
        var totalAmountSpend = 0.0
        list.forEach {
            totalAmountSpend += it.Money
        }
        binding.txtTotalAmount.text = "-${decimalFormat.format(totalAmountSpend)}"
    }
    private fun bindDataAgain(){
        setOfNames.clear()
        binding.HistoryRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.HistoryRecyclerview.adapter = historySpendAdapter
        viewModel.getAllHistorySpend()
        setOfNames.add("Tất cả")
        viewModel.listHistorySpend.observe(viewLifecycleOwner){
            listHistory = it
            bindAdapter(it)
            totalAmount(it)
            it.forEach { historySpend ->
                setOfNames.add(historySpend.TypeSpend)
            }
        }
    }
    private fun bindAdapter(listHistorySPend: List<HistorySpend>){
        historySpendAdapter.submitList(listHistorySPend)
    }
}