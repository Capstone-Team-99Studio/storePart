package com.example.cap.dataclass

import com.google.gson.annotations.SerializedName


data class StoreInfo(
    @SerializedName("name") val storeName: String,
    @SerializedName("phoneNumber") val storeNum: String,
    @SerializedName("foodOrigin") val originFood: String,
    @SerializedName("number") val onerNum:String,
    @SerializedName("introduce") val storeIntro: String,
)

