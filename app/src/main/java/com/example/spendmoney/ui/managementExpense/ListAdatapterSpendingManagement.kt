package com.example.spendmoney.ui.managementExpense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spendmoney.base.BaseAdapter
import com.example.spendmoney.base.BaseViewHolder
import com.example.spendmoney.databinding.ItemTypeSpendBinding
import com.example.spendmoney.models.ObjSpend

class ListAdapterSpendingManagement(private val onClick: (ObjSpend) -> Unit, private val isVisibility: Boolean) : BaseAdapter<ObjSpend, BaseViewHolder<ObjSpend>>(ObjSpend.differUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ObjSpend> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTypeSpendBinding.inflate(inflater, parent, false)

        if(isVisibility){
            binding.imgDelete.visibility = View.VISIBLE
        }else{
            binding.imgDelete.visibility = View.INVISIBLE
        }

        return ViewHolder(binding)
    }
    inner class ViewHolder(val binding: ItemTypeSpendBinding) :
        BaseViewHolder<ObjSpend>(binding) {

        override fun binView(item: ObjSpend, isItemSelected: Boolean) {
            super.binView(item, isItemSelected)
            binding.apply {
                txtNameTypeSpend.text = item.NameObjSpend
                imgIconTypeSpend.setImageResource(item.ImgObjSpend!!)
                imgDelete.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }

}