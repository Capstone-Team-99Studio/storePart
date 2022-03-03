package com.example.cap.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cap.CustomDialog
import com.example.cap.ReviewActivity
import com.example.cap.databinding.ReviewListBinding
import com.example.cap.dataclass.reviewData

class reviewAdapter(val context: CustomDialog, val activity: ReviewActivity): RecyclerView.Adapter<reviewHolder>(){

    var reviewList : Array<reviewData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): reviewHolder {
        val binding = ReviewListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return reviewHolder(binding)
    }

    override fun onBindViewHolder(holder: reviewHolder, position: Int) {
        val menu = reviewList?.get(position)
        holder.setMenu(menu)

        holder.itemView.setOnClickListener(){
            context.show(activity.supportFragmentManager, "CustomDialogStore")
        }


    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    override fun getItemCount(): Int {
        return reviewList?.size?: 0
    }
}

class reviewHolder(val binding: ReviewListBinding): RecyclerView.ViewHolder(binding.root)
{

    init {
        itemView.setOnClickListener(){}
    }
    fun setMenu(menu: reviewData?){
        menu?.let{
                //binding.foodname.text = menu.food_name

        }
    }

}