package com.example.lab06_gianfranco_traverso

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustemAdapter(val itemList: MutableList<Item>) : RecyclerView.Adapter<CustemAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewName = itemView.findViewById(R.id.nameText) as TextView
        val textViewPrice = itemView.findViewById(R.id.priceText) as TextView
        val imageView = itemView.findViewById(R.id.imageView2) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = itemList[position]

        val url = item.url
        Picasso.get().load(url).into(holder.imageView)
        holder.textViewName.text = item.name
        holder.textViewPrice.text = item.price.toString()
    }
}