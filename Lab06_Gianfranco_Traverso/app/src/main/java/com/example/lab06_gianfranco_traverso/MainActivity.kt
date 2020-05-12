package com.example.lab06_gianfranco_traverso

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_dialog.view.*
import kotlinx.android.synthetic.main.item_layout.*
import kotlinx.android.synthetic.main.item_layout.view.*
import java.io.File

class MainActivity : AppCompatActivity(), OnItemClickListener {
    var count = 0
    var sum = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        val items : MutableList<Item> = mutableListOf<Item>()
        val descriptions : MutableList<String> = mutableListOf<String>()

        val file = File(this.filesDir, "cart.txt")
        if (file.exists()) { file.bufferedReader().forEachLine {
                val objects = it.split(", ")
                count += objects[2].toInt()
                sum += objects[1].toInt()*objects[2].toInt()
            }

            itemsCountText.text = (count.toString()+" articulos")
            totalPriceText.text = ("$"+sum.toString())
        }

        this.assets.open("products.txt").bufferedReader().forEachLine {
            val objects = it.split(", ")
            items.add(Item(name = objects[0], price = objects[1].toInt(), url = objects[2], description = objects[3]))
            descriptions.add(objects[3])
        }

        val adapter = CustemAdapter(items, this)
        recyclerView.adapter =adapter
    }

    override fun onItemClick(item: Item, position: Int) {
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.item_dialog, null)
        val price = item.price.toString()
        val name = item.name
        val number = dialogView.findViewById<EditText>(R.id.amountItemText)
        dialogView.findViewById<TextView>(R.id.nameText).text = ("Nombre:    "+name)
        dialogView.findViewById<TextView>(R.id.priceText).text = ("Precio:       $"+price)
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        dialog.setPositiveButton("Confirmar", { dialogInterface: DialogInterface?, which: Int ->  })
        dialog.setNegativeButton("Cancelar", { dialogInterface: DialogInterface?, which: Int ->  })
        val customDialog = dialog.create()
        customDialog.show()
        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            var amount = 0
            if(number.text.toString() == ""){
                amount = 1
            }
            else{
                amount = number.text.toString().toInt()
            }
            sum += price.toInt()*amount
            count += amount
            itemsCountText.text = (count.toString()+" articulos")
            totalPriceText.text = ("$"+sum.toString())

            var file = File(this.filesDir, "cart.txt")
            if (file.exists()){
                file.appendText(name+", "+price+", "+amount.toString()+"\n")
            }
            else{
                file.createNewFile()
                file.appendText(name+", "+price+", "+amount.toString()+"\n")
            }
            customDialog.dismiss()
        }
    }

    fun itemClick(view: View){
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.item_dialog, null)
        val price = view.findViewById<TextView>(R.id.priceText).text.toString()
        val name = view.findViewById<TextView>(R.id.nameText).text.toString()
        val number = dialogView.findViewById<EditText>(R.id.amountItemText)
        dialogView.findViewById<TextView>(R.id.nameText).text = ("Nombre:    "+name)
        dialogView.findViewById<TextView>(R.id.priceText).text = ("Precio:       $"+price)
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        dialog.setPositiveButton("Confirmar", { dialogInterface: DialogInterface?, which: Int ->  })
        dialog.setNegativeButton("Cancelar", { dialogInterface: DialogInterface?, which: Int ->  })
        val customDialog = dialog.create()
        customDialog.show()
        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            var amount = 0
            if(number.text.toString() == ""){
                amount = 1
            }
            else{
                amount = number.text.toString().toInt()
            }
            sum += price.toInt()*amount
            count += amount
            itemsCountText.text = (count.toString()+" articulos")
            totalPriceText.text = ("$"+sum.toString())

            var file = File(this.filesDir, "cart.txt")
            if (file.exists()){
                file.appendText(name+", "+price+", "+amount.toString()+"\n")
            }
            else{
                file.createNewFile()
                file.appendText(name+", "+price+", "+amount.toString()+"\n")
            }
            customDialog.dismiss()
        }
    }

    fun cartClick(view: View){
        val intent = Intent(view.context, CartProduc::class.java)
        view.context.startActivity(intent)
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putString("savedCountText", itemsCountText.text.toString())
        savedInstanceState.putString("savedTotalText", totalPriceText.text.toString())
        savedInstanceState.putInt("savedCount", count)
        savedInstanceState.putInt("savedTotal", sum)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        itemsCountText.text = savedInstanceState?.getString("savedCountText")
        totalPriceText.text = savedInstanceState?.getString("savedTotalText")
        count = savedInstanceState!!.getInt("savedCount")
        sum = savedInstanceState!!.getInt("savedTotal")
    }
}
