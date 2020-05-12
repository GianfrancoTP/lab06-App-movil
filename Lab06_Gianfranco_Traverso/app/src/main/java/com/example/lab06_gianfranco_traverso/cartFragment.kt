package com.example.lab06_gianfranco_traverso

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.lab06_gianfranco_traverso.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_cart_product.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cart.*
import java.io.File

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [cartFragment.OnListFragmentInteractionListener] interface.
 */
class cartFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    var count = 0
    var sum = 0
    var list= mutableListOf<String>()
    val products = arrayListOf<Product>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cart_list, container, false)
        val button = activity?.findViewById<ImageView>(R.id.editImageView)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> androidx.recyclerview.widget.LinearLayoutManager(context)
                    else -> androidx.recyclerview.widget.GridLayoutManager(context, columnCount)
                }
                val file = File(activity?.filesDir, "cart.txt")
                if (file.exists()) {
                    file.bufferedReader().forEachLine {
                        val objects = it.split(", ")
                        val p = Product(name = objects[0], price = objects[1], amount = objects[2].toInt())
                        if (list.contains(objects[0])) {
                            for (i:Product in products){
                                if (i.name == p.name){
                                    i.increment(objects[2].toInt())
                                    break
                                }
                            }
                        } else {
                            list.add(objects[0])
                            products.add(p)
                        }
                        count += objects[2].toInt()
                        sum += objects[1].toInt() * objects[2].toInt()
                    }
                    activity?.findViewById<TextView>(R.id.totalAmountTextView)?.text = (count.toString()+" articulos")
                    activity?.findViewById<TextView>(R.id.totalPriceTextView)?.text = ("$"+sum.toString())
                }
                adapter = MyProductRecyclerViewAdapter(products, listener)

            }
        }

        if (button != null) {
            button.setOnClickListener {
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Product)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(): cartFragment {
            return cartFragment()
        }
    }
}
