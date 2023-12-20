package com.example.spendmoney.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class TypeSpending(
    val id: String,
    val NameTypeSpending: String,
    val ImgTypeSpending: String,
):Parcelable {
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<TypeSpending>(){
            override fun areItemsTheSame(oldItem: TypeSpending, newItem: TypeSpending) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TypeSpending, newItem: TypeSpending) =
                oldItem.id  == newItem.id
        }
    }
}