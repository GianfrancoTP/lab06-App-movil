package com.example.lab06_gianfranco_traverso

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class CustemAdapter(val itemList: MutableList<Item>, var clickListener: OnItemClickListener) : RecyclerView.Adapter<CustemAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewName = itemView.findViewById(R.id.nameText) as TextView
        val textViewPrice = itemView.findViewById(R.id.priceText) as TextView
        val imageView = itemView.findViewById(R.id.imageView2) as ImageView
        val description = itemView.findViewById(R.id.descriptionTextView) as TextView

        fun initialize(item: Item, action:OnItemClickListener){
            textViewName.text = item.name
            textViewPrice.text = item.price.toString()
            description.text = item.description
            Picasso.get().load(item.url).into(imageView)

            itemView.setOnClickListener{
                action.onItemClick(item, adapterPosition)
            }

            itemView.DescriptionButton.setOnClickListener {
                val v = itemView.descriptionTextView
                v.visibility = (if (v.visibility == View.VISIBLE){
                    View.INVISIBLE
                } else{
                    View.VISIBLE
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item: Item = itemList[position]
//
//        val url = item.url
//        holder.description.text = item.description
//
//        Picasso.get().load(url).into(holder.imageView)
//        holder.textViewName.text = item.name
//        holder.textViewPrice.text = item.price.toString()
        holder.initialize(itemList.get(position), clickListener)
    }
}

interface OnItemClickListener{
    fun onItemClick(item: Item, position: Int)
}

