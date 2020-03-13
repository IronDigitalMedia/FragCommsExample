package com.example.fragcommsexample

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    val text = itemView.item_text

    fun bind(item: Item){

        text.text = item.text

    }



}