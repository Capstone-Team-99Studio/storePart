package com.example.cap.retrofit2

import com.example.cap.dataclass.Data
import com.example.cap.dataclass.FoodListDto
import com.example.cap.dataclass.example
import retrofit2.Call
import retrofit2.http.*

interface APIfood {
    @GET("store/8")
    fun get_foods(): Call<example>

    @POST("food/1")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun post_foods(
        @Body jsonparams: FoodListDto
    ): Call<FoodListDto>
}