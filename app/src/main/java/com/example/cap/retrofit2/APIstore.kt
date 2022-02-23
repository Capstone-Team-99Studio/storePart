package com.example.cap.retrofit2

import com.example.cap.dataclass.Data
import retrofit2.Call
import retrofit2.http.*

interface APIstore {

    @GET("stores")
    fun get_users(): Call<Data>

    @POST("store")
    @Headers("accept: application/json",
        "content-type: application/json")

    fun post_users(
        @Body jsonparams: Data
    ): Call<Data>

}
