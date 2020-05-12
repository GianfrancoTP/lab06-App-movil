package com.example.lab06_gianfranco_traverso

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ColorStateListInflaterCompat.inflate


import com.example.lab06_gianfranco_traverso.cartFragment.OnListFragmentInteractionListener
import com.example.lab06_gianfranco_traverso.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import java.io.File

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
        val v1 = LayoutInflater.from(parent?.context).inflate(R.layout.item_dialog, parent, false)
        return ViewHolder(v, v1)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.textViewName.text = item.name
        holder.textViewPrice.text = item.price
        holder.textViewAmount.text = ("Cantidad: "+item.amount.toString())
        holder.textViewTotal.text = ("$"+(item.amount*item.price.toInt()).toString())
        holder.initialize(mValues.get(position))
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View, val v: View) : RecyclerView.ViewHolder(mView) {
        val textViewName = mView.findViewById(R.id.nameText1) as TextView
        val textViewPrice = mView.findViewById(R.id.priceText1) as TextView
        val textViewTotal = mView.findViewById(R.id.totalTextView) as TextView
        val textViewAmount = mView.findViewById(R.id.amountTextView) as TextView

        fun initialize(item: Product){
            mView.editImageView.setOnClickListener {
                val dialog = androidx.appcompat.app.AlertDialog.Builder(v.context)
                val dialogView = v
                val name = item.name
                val price = item.price
                val number = dialogView.findViewById<EditText>(R.id.amountItemText)
                dialogView.findViewById<TextView>(R.id.nameText).text = ("Nombre:    "+name)
                dialogView.findViewById<TextView>(R.id.priceText).text = ("Precio:       $"+price)
                dialog.setView(dialogView)
                dialog.setCancelable(false)
                dialog.setPositiveButton("Confirmar", { dialogInterface: DialogInterface?, which: Int ->  })
                dialog.setNegativeButton("Cancelar", { dialogInterface: DialogInterface?, which: Int ->  })
                val customDialog = dialog.create()
                customDialog.show()
                customDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setOnClickListener{
                    var amount = 0
                    if(number.text.toString() == ""){
                        amount = 0
                    }
                    else{
                        amount = number.text.toString().toInt()
                    }
                    customDialog.dismiss()
                }
            }
        }
    }
}

data class Product(val name:String, val price:String, var amount: Int) {
    fun increment(size: Int){
        this.amount += size
    }
}
