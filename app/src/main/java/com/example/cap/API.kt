package com.example.cap

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.http.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface API {
    @POST("/store")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun post_users(
        @Body jsonparams: StoreInfo
    ): Call<StoreInfo>

    @GET("/store")
    @Headers("accept: application/json",
        "content-type: application/json"
    )
    fun get_users(
    ): Call<StoreInfo>


    companion object {
        // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val BASE_URL = "https://da86-125-180-55-163.ngrok.io" // 주소

        fun create(): API {


            val gson : Gson =  GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
//               .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(API::class.java)
        }
    }
}