package com.example.spendmoney.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spendmoney.data.local.DataBaseLocal
import kotlinx.parcelize.Parcelize

@Entity(tableName = DataBaseLocal.TABLE_HISTORY)
data class HistorySpend(
    @PrimaryKey
    val id: String,
    val idTypeSpend: String,
    val TypeSpend: String,
    val Money: Double,
    val DaySpend: String,
    val Img: Int?,
    val Content: String
){
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<HistorySpend>(){
            override fun areItemsTheSame(oldItem: HistorySpend, newItem: HistorySpend): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: HistorySpend, newItem: HistorySpend): Boolean =
                oldItem.id == newItem.id
        }
    }
}
