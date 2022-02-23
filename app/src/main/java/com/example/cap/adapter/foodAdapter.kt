package com.example.cap.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cap.R
import com.example.cap.dataclass.FoodListDto

class foodAdapter(val context: Context, private val items: Array<FoodListDto>) : RecyclerView.Adapter<foodAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.fooditem, parent, false)
        return ViewHolder(inflatedView)
    }

    //ViewHolder 단위 객체로 View의 데이터를 설정합니다
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        init {
            v.setOnClickListener {
            }
        }


        val title = v.findViewById<TextView>(R.id.foodname)
        val price = v.findViewById<TextView>(R.id.costText)
        //val intro = v.findViewById<TextView>(R.id.foodexplan)

    }
    //생성된 View에 보여줄 데이터를 설정
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        Log.d("recyclerview ", "${item}")
        holder.title.text = item.food_name
        holder.price.text = item.food_price
        //holder.intro.text = item.food_status


        holder.itemView.setOnClickListener(){
        }

    }


}