package com.example.cap.dataclass


data class store(
    var food_origin:String,
    var data : List<Test>
)
data class Test (
    var name:String,
    var number: String,
    var phone_number: String,
    var introduce: String,
    //var image:String,
)