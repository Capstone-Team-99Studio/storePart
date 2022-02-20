package com.example.cap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cap.adapter.foodAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.cap.databinding.ActivityMyStoreBinding
import com.example.cap.retrofit2.APIfood
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyStore : AppCompatActivity() {
    val binding by lazy { ActivityMyStoreBinding.inflate(layoutInflater)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://da86-125-180-55-163.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(APIfood::class.java)

        retrofitService.get_foods().enqueue(object : Callback<FoodData>{
            override fun onResponse(call: Call<FoodData>, response: Response<FoodData>) {
                Log.d("foodlist", "${response.body()}")
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        binding.flist.apply {
                            layoutManager = LinearLayoutManager(this@MyStore)
                            adapter = foodAdapter(context, it.fooddata)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<FoodData>, t: Throwable) {
                Log.d("foodlist", "통신 에러")
            }


        })




        binding.myStoreLayout.setOnClickListener(){
            val storeDialog = CustomDialogStore()
            storeDialog.show(supportFragmentManager, "CustomDialogStore")

        }

        //화면 넘김
        binding.menuSingBtn1.setOnClickListener({
            val menuintent = Intent(this, FoodRegister::class.java)
            startActivity(menuintent)
        })
    }
}