package com.example.cap.retrofit2

import com.example.cap.Food
import com.example.cap.FoodData
import com.example.cap.dataclass.StoreInfo
import retrofit2.Call
import retrofit2.http.*

interface APIfood {
    @GET("store/8")
    fun get_foods(): Call<FoodData>

    @POST("store/1")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun post_foods(
        @Body jsonparams: Food
    ): Call<Food>
}