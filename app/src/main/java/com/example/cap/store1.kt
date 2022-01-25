package com.example.cap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cap.databinding.ActivityMainBinding
import com.example.cap.databinding.ActivityStore1Binding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_store1.*

class store1 : AppCompatActivity() {

    val binding by lazy { ActivityStore1Binding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(binding.root)

        binding.storeSinginBtn.setOnClickListener({
            val store2intent = Intent(this, Store2::class.java)
            startActivity(store2intent)
        })
    }
}