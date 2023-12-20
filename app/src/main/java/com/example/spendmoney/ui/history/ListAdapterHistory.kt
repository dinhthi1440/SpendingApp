package com.example.spendmoney.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseAdapter
import com.example.spendmoney.base.BaseViewHolder
import com.example.spendmoney.databinding.ItemHistorySpendBinding
import com.example.spendmoney.models.HistorySpend
import java.text.DecimalFormat

class ListAdapterHistory ():BaseAdapter<HistorySpend,
        BaseViewHolder<HistorySpend>>(HistorySpend.differUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<HistorySpend> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistorySpendBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemHistorySpendBinding) :
        BaseViewHolder<HistorySpend>(binding) {
        override fun binView(item: HistorySpend, isItemSelected: Boolean) {
            super.binView(item, isItemSelected)
            val decimalFormat = DecimalFormat("#,###.###")
            binding.apply {
                txtNameObjSpend.text = item.TypeSpend
                txtDay.text = item.DaySpend
                txtMoney.text = "-" + decimalFormat.format(item.Money.toDouble())
                txtContentSpend.text = item.Content
            }
        }
    }
}