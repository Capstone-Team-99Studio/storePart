package com.example.cap.dataclass

data class Data(
    val foodListDtoList: Array<FoodListDto>,
    val foodOrigin: String,
    val introduce: String,
    val name: String,
    val number: String,
    val phoneNumber: String
)