package com.example.cap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.cap.databinding.ActivityMyStoreBinding

class MyStore : AppCompatActivity() {
    val binding by lazy { ActivityMyStoreBinding.inflate(layoutInflater)}
    val api = API.create();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val list = ArrayList<foodlist>()
        api.get_users().enqueue(object : Callback<StoreInfo>{
            override fun onResponse(call: Call<StoreInfo>, response: Response<StoreInfo>) {
                Log.d("log",response.toString())
                Log.d("log", response.body().toString())
                if(!response.body().toString().isEmpty())
                    binding.storeIntro2.setText(response.body()?.storeName.toString());
                    binding.storeIntroDetail.setText(response.body()?.storeIntro.toString());
            }

            override fun onFailure(call: Call<StoreInfo>, t: Throwable) {
                Log.d("log",t.message.toString())
                Log.d("log","fail")
            }

        })


        //화면 넘김
        binding.menuSingBtn1.setOnClickListener({
            val menuintent = Intent(this, FoodRegister::class.java)
            startActivity(menuintent)
        })
    }
}