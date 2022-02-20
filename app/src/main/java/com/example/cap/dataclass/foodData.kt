package com.example.cap

import com.google.gson.annotations.SerializedName

data class FoodData(
    @SerializedName("data") var fooddata : List<Food>
)

data class Food(
    @SerializedName("name") val food_name:String,
    @SerializedName("price") val food_price:String,
    @SerializedName("status") val food_status:String,
    //@SerializedName("storeName") val store_name:String,
    //@SerializedName("reviewList") val reviewData: List<reviewData>
    )

data class reviewData(
    @SerializedName("name") val user_name:String,
    @SerializedName("review") val user_review:String,
    @SerializedName("rating") val user_rating:String,

    )
