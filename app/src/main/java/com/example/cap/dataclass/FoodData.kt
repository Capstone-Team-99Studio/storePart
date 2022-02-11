package com.example.cap

import com.google.gson.annotations.SerializedName

data class FoodData(
    var data : List<Food>
)

data class Food(
    @SerializedName("name") val food_name:String,
    @SerializedName("price") val food_price:String,
    @SerializedName("status") val food_status:String,
    @SerializedName("menuintro") val food_intro:String,

    )
