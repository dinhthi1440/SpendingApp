package com.example.spendmoney.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.text.DecimalFormat

abstract class BaseViewHolder<T>(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var item: T? = null
    protected val decimalFormat = DecimalFormat("#,###.###")


    open fun binView(item: T,isItemSelected: Boolean) {
        this.item = item
    }
}