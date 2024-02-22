package com.example.spendmoney.ui.newObjSPend

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.spendmoney.R


class ListAdapterImgObjSpend( private val currentImg: Int, private val listImg: List<Int>,  private val onItemClickListener: (Int) -> Unit)
    :RecyclerView.Adapter<ListAdapterImgObjSpend.ImgViewHolder>() {
        class ImgViewHolder(view: View): RecyclerView.ViewHolder(view){
            val img:ImageView = view.findViewById(R.id.img_icon_typeSpend)
            val checkItemVisibility: ImageView = view.findViewById(R.id.check_item_visibility)
            val backgroundItem: ConstraintLayout = view.findViewById(R.id.bg_color_change_selected)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_obj_spend_img, parent, false )
        return ImgViewHolder(view)
    }
    private var itemIndexSelected: Int = -1
    private var itemCurrentImgDefault = -1
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ImgViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.img.setImageResource(listImg[position])
        Log.e("Check", "onBindViewHolder: ${listImg[position]} và $currentImg và $itemIndexSelected", )

        if (itemIndexSelected == position || itemIndexSelected==-1 && currentImg == listImg[position] ) {
            if(itemIndexSelected ==-1 ){
                itemCurrentImgDefault = position
            }
            Log.e("TAG", "onBindViewHolder: đã đi vào trường hợp", )
            holder.checkItemVisibility.visibility = View.VISIBLE
            holder.backgroundItem.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.item_spending_after_selected))
        } else {
            holder.checkItemVisibility.visibility = View.INVISIBLE
            holder.backgroundItem.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.item_spending_before_selected))
        }
        holder.itemView.setOnClickListener {
            val previousSelectedIndex = itemIndexSelected
            itemIndexSelected = holder.adapterPosition
            Log.e("TAG", "onBindViewHolder: vị trí: ${holder.adapterPosition} và item=${itemIndexSelected}")
            notifyItemChanged(previousSelectedIndex)
            notifyItemChanged(itemCurrentImgDefault)
            notifyItemChanged(itemIndexSelected)
            onItemClickListener.invoke(listImg[position])
        }

    }

    override fun getItemCount(): Int = listImg.size



}