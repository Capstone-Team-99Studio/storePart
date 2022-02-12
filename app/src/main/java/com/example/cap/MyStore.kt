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
import com.example.cap.retrofit2.API

class MyStore : AppCompatActivity() {
    val binding by lazy { ActivityMyStoreBinding.inflate(layoutInflater)}
    val api = API.create();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


         api.get_foods("food").enqueue(object : Callback<FoodData>{
            override fun onResponse(call: Call<FoodData>, response: Response<FoodData>) {
                Log.d("log", response.toString())
                Log.d("log", response.body().toString())
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        binding.flist.apply {
                            layoutManager = LinearLayoutManager(this@MyStore)
                            adapter = foodAdapter(it.data)
                        }
                    }
                }
            }


            override fun onFailure(call: Call<FoodData>, t: Throwable) {
                Log.d("log",t.message.toString())
                Log.d("log","fail")            }

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