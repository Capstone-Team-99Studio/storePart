package com.example.cap.dataclass

import com.google.gson.annotations.SerializedName

data class reviewData(
    @SerializedName("name") val food_name:String,
    @SerializedName("price") val food_price:String,
    @SerializedName("status") val food_status:String,

    )
