package com.example.spendmoney.ui.objspend

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import java.text.DecimalFormat
import android.view.ViewGroup
import com.example.spendmoney.R
import com.example.spendmoney.base.BaseAdapter
import com.example.spendmoney.base.BaseViewHolder
import com.example.spendmoney.databinding.ItemObjectSpendBinding
import com.example.spendmoney.models.ObjSpend

class ListAdapterObjSpend(private val onClick: (ObjSpend) -> Unit,
                          private val onClickEdit: (ObjSpend) -> Unit,private val isVisibility: Boolean)  : BaseAdapter<ObjSpend, BaseViewHolder<ObjSpend>>(ObjSpend.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ObjSpend> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemObjectSpendBinding.inflate(inflater, parent, false)
        if(isVisibility){
            binding.imgEdit.visibility = View.VISIBLE
        }else{
            binding.imgEdit.visibility = View.GONE
        }
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemObjectSpendBinding) :
            BaseViewHolder<ObjSpend>(binding) {
        override fun binView(item: ObjSpend, isItemSelected: Boolean) {
            super.binView(item, isItemSelected)
            binding.apply {
                txtNameObjSpend.text = item.NameObjSpend
                txtMoneyDatieu.text = "-" + decimalFormat.format(item.MoneyDaTieu) + "đ"
                txtMoneyConlai.text = decimalFormat.format(item.moneyConLai) + "đ"
                txtNumberOfProgressObjSpend.text = "${item.numberOfProgress}%"
                totalMoney.text = decimalFormat.format(item.MoneyBanDau) + "đ"
                progressObjSpend.progress = item.numberOfProgress.toInt()
                imgObjSpend.setImageResource(item.ImgObjSpend!!)
                root.setOnClickListener {
                    onClick(item)
                }
                imgEdit.setOnClickListener {
                    onClickEdit(item)
                }
            }
            Log.e("TAG", "binView: Hàn nha tứ", )
        }
    }


}