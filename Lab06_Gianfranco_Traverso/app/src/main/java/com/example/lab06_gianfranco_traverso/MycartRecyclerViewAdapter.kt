package com.example.lab06_gianfranco_traverso

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import com.example.lab06_gianfranco_traverso.cartFragment.OnListFragmentInteractionListener
import com.example.lab06_gianfranco_traverso.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_cart.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyProductRecyclerViewAdapter(
    private val mValues: List<Product>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.fragment_cart, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.textViewName.text = item.name
        holder.textViewPrice.text = item.price
        holder.textViewAmount.text = ("Cantidad: "+item.amount.toString())
        holder.textViewTotal.text = ("$"+(item.amount*item.price.toInt()).toString())
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val textViewName = mView.findViewById(R.id.nameText1) as TextView
        val textViewPrice = mView.findViewById(R.id.priceText1) as TextView
        val textViewTotal = mView.findViewById(R.id.totalTextView) as TextView
        val textViewAmount = mView.findViewById(R.id.amountTextView) as TextView
    }
}

data class Product(val name:String, val price:String, var amount: Int) {
    fun increment(size: Int){
        this.amount += size
    }
}
