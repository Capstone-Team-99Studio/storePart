package com.example.cap.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cap.CustomDialog
import com.example.cap.MyStore
import com.example.cap.R
import com.example.cap.databinding.FooditemBinding
import com.example.cap.dataclass.FoodListDto

class exampleAdapter(val context: CustomDialog,val activity: MyStore): RecyclerView.Adapter<Holder>(){

    var menuList : Array<FoodListDto>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = FooditemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val menu = menuList?.get(position)
        holder.setMenu(menu)

        holder.itemView.setOnClickListener(){
            context.show(activity.supportFragmentManager, "CustomDialogStore")
        }


    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    override fun getItemCount(): Int {
        return menuList?.size?: 0
    }
}

class Holder(val binding: FooditemBinding): RecyclerView.ViewHolder(binding.root)
{

    init {
        itemView.setOnClickListener(){}
    }
    fun setMenu(menu: FoodListDto?){
        menu?.let{
            binding.foodname.text = menu.food_name
            binding.costText.text = menu.food_price
            binding.foodexplan.text = menu.food_status
        }
    }

}