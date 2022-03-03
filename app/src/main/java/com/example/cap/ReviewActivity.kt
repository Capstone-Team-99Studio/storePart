package com.example.cap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cap.adapter.exampleAdapter
import com.example.cap.adapter.reviewAdapter
import com.example.cap.databinding.ActivityReviewBinding
import com.example.cap.dataclass.example
import com.example.cap.retrofit2.APIfood
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReviewActivity : AppCompatActivity() {

    val binding by lazy { ActivityReviewBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = reviewAdapter(CustomDialog(), this@ReviewActivity)
        binding.reviewList.adapter = adapter
        binding.reviewList.layoutManager =  LinearLayoutManager(this@ReviewActivity)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://da86-125-180-55-163.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(APIfood::class.java)

      /*  retrofitService.get_foods().enqueue(object : Callback<example> {
            override fun onResponse(call: Call<example>, response: Response<example>) {
                adapter.reviewList = response.body()?.data?.foodListDtoList
                adapter.notifyDataSetChanged()
                Log.d("foodlist", "${response.body()?.data?.foodListDtoList}")
            }

            override fun onFailure(call: Call<example>, t: Throwable) {
                Log.d("foodlist", "통신 에러")
            }

        })
        */
    }
}