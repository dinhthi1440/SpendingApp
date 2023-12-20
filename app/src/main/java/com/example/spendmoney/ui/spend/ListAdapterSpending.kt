package com.example.spendmoney.ui.spend

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseAdapter
import com.example.spendmoney.base.BaseViewHolder
import com.example.spendmoney.databinding.ItemTypeSpendBinding
import com.example.spendmoney.models.TypeSpending

class ListAdapterSpending(private val onClick: (TypeSpending) -> Unit) : BaseAdapter<TypeSpending, BaseViewHolder<TypeSpending>>(TypeSpending.differUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<TypeSpending> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTypeSpendBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    inner class ViewHolder(val binding: ItemTypeSpendBinding) :
        BaseViewHolder<TypeSpending>(binding) {

        override fun binView(item: TypeSpending, isItemSelected: Boolean) {
            super.binView(item, isItemSelected)
            binding.apply {
                txtNameTypeSpend.text = item.NameTypeSpending
                root.setOnClickListener {
                    onClick(item)
                    //bgColorChangeSelected.setBackgroundColor(R.color.item_spending_when_selected)
                }

            }
        }
    }

}