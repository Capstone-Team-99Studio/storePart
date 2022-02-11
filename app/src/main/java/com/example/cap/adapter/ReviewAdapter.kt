package com.example.cap.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cap.Food
import com.example.cap.R
import com.example.cap.dataclass.reviewData

class reviewAdapter (private val items: List<reviewData>) : RecyclerView.Adapter<reviewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): reviewAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.review_list, parent, false)
        return ViewHolder(inflatedView)
    }


    //ViewHolder 단위 객체로 View의 데이터를 설정합니다
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        init {
            v.setOnClickListener {
            }
        }


    }


    override fun onBindViewHolder(holder: reviewAdapter.ViewHolder, position: Int) {
        val item = items[position]



    }


}