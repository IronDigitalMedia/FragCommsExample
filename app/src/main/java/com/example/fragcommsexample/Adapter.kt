package com.example.fragcommsexample

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter : RecyclerView.Adapter<CustomViewHolder>() {

    val TAG:String = "Adapter"
    lateinit var item:Item
    var items = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(tempItems: MutableList<Item>){
        items.clear()
        items.addAll(tempItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        item  = items.get(position)
        holder.bind(item)
        Log.i(TAG, "$item")
    }


}