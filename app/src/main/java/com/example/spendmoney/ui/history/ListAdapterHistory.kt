package com.example.spendmoney.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseAdapter
import com.example.spendmoney.base.BaseViewHolder
import com.example.spendmoney.databinding.ItemHistorySpendBinding
import com.example.spendmoney.models.HistorySpend
import com.example.spendmoney.models.ObjSpend
import java.text.DecimalFormat

class ListAdapterHistory (private val onClick: (HistorySpend) -> Unit):BaseAdapter<HistorySpend,
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
                txtMoney.text = "-" + decimalFormat.format(item.Money)
                txtContentSpend.text = item.Content
                imgObjSpend.setImageResource(item.Img!!)
            }

            binding.imgDots.setOnClickListener {
                showPopupMenu(it, item)
            }
        }

        private fun showPopupMenu(view: View, item: HistorySpend){
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.inflate(R.menu.menu_history_spend)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.item_delete -> {
                        onClick(item)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

}