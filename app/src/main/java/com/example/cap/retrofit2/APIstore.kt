package com.example.cap.retrofit2

import com.example.cap.dataclass.StoreInfo
import com.example.cap.dataclass.store
import retrofit2.Call
import retrofit2.http.*

interface APIstore {

    @GET("stores")
    fun get_users(): Call<StoreInfo>

    @POST("store")
    @Headers("accept: application/json",
        "content-type: application/json")

    fun post_users(
        @Body jsonparams: StoreInfo
    ): Call<StoreInfo>

}
