package com.example.cap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fooditem.view.*

class RecyclerUserAdapter(private val items: ArrayList<foodlist>) : RecyclerView.Adapter<RecyclerUserAdapter.ViewHolder>() {

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: RecyclerUserAdapter.ViewHolder, position: Int) {

            val item = items[position]
            val listener = View.OnClickListener { it ->
                Toast.makeText(it.context, "Clicked -> ID : ${item.name}, intro : ${item.explan}", Toast.LENGTH_SHORT).show()
            }
            holder.apply {
                bind(listener, item)
                itemView.tag = item
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.fooditem, parent, false)
            return RecyclerUserAdapter.ViewHolder(inflatedView)
        }

        // 각 항목에 필요한 기능을 구현
        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            private var view: View = v
            fun bind(listener: View.OnClickListener, item: foodlist) {
                view.foodname.text = item.name
                view.foodexplan.text = item.explan
                view.setOnClickListener(listener)
            }
        }


}