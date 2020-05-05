package com.example.lab06_gianfranco_traverso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.File

class CartProduc : AppCompatActivity(), cartFragment.OnListFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.cart, cartFragment.newInstance(), "productList").commit()
        }
    }

    override fun onListFragmentInteraction(item: Product) {
        TODO()
    }
}
