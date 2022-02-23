package com.example.cap.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cap.databinding.FooditemBinding
import com.example.cap.dataclass.Data
import com.example.cap.dataclass.FoodListDto

class exampleAdapter: RecyclerView.Adapter<Holder>(){

    var menuList : Array<FoodListDto>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = FooditemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val menu = menuList?.get(position)
        holder.setMenu(menu)
    }

    override fun getItemCount(): Int {
        return menuList?.size?: 0
    }
}

class Holder(val binding: FooditemBinding): RecyclerView.ViewHolder(binding.root)
{

    fun setMenu(menu: FoodListDto?){
        menu?.let{
            binding.foodname.text = menu.food_name
            binding.costText.text = menu.food_price
            binding.foodexplan.text = menu.food_status
        }
    }
}