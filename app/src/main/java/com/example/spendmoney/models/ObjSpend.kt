package com.example.spendmoney.models

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spendmoney.data.local.DataBaseLocal

@Entity(tableName = DataBaseLocal.TABLE_OBJSPEND)
class ObjSpend(
    @PrimaryKey
    val id: String,
    val NameObjSpend: String,
    val MoneyBanDau: Double,
    val MoneyDaTieu: Double?,
    val ImgObjSpend: Int?,
){
    val moneyConLai: Double
        get() = MoneyBanDau - MoneyDaTieu!!
    val numberOfProgress: Float
        get() = (((moneyConLai?.toInt()!! * 100.0 / MoneyBanDau.toInt()) *100).toInt() / 100.0).toFloat()
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<ObjSpend>(){
            override fun areItemsTheSame(oldItem: ObjSpend, newItem: ObjSpend): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ObjSpend, newItem: ObjSpend): Boolean =
                oldItem.id == newItem.id
        }
    }
}
