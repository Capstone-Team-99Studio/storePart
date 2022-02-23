package com.example.cap

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.cap.databinding.ActivityFoodRegisterBinding
import com.example.cap.dataclass.FoodListDto
import com.example.cap.retrofit2.API
import com.example.cap.retrofit2.APIfood
import com.example.cap.retrofit2.APIstore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class FoodRegister : AppCompatActivity() {

    val binding by lazy { ActivityFoodRegisterBinding.inflate(layoutInflater)}
    val api = API.create()

    var photoUri: Uri? = null
    lateinit var cameraPermission: ActivityResultLauncher<String>
    lateinit var storagePermission: ActivityResultLauncher<String>

    lateinit var cameraLauncher: ActivityResultLauncher<Uri>
    lateinit var galleryLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://da86-125-180-55-163.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(APIfood::class.java)

        storagePermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    setViews()
                } else {
                    Toast.makeText(baseContext, "외부저장소 권한을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_LONG)
                        .show()
                    finish()
                }
            }

        cameraPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    openCamera()
                } else {
                    Toast.makeText(baseContext, "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.", Toast.LENGTH_LONG)
                        .show()
                }
            }

        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
                if (isSuccess) {
                    binding.menuImg.setImageURI(photoUri)
                }
            }

        galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding.menuImg.setImageURI(uri)
        }

        storagePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)



        binding.menuSignUpBtn.setOnClickListener({
            val fooddata = FoodListDto(
                binding.menuNameIp.text.toString(),
                binding.costIp.text.toString(),
                binding.costIp.text.toString()
                //binding.tasteTextIp.text.toString(),
                //binding.menuIntroIp.text.toString(),

            )

            retrofitService.post_foods(fooddata).enqueue(object : Callback<FoodListDto> {
                override fun onResponse(call: Call<FoodListDto>, response: Response<FoodListDto>) {
                    Log.d("Foodpost", response.toString())
                    Log.d("Foodpost", response.body().toString())
                    if(!response.body().toString().isEmpty()){
                        binding.textView11.setText(response.body().toString())
                    }
                }

                override fun onFailure(call: Call<FoodListDto>, t: Throwable) {
                    Log.d("Foodpost", t.message.toString())
                    Log.d("Foodpost", "fail")
                }

            })

            val returnMyStoreintent = Intent(this, MyStore::class.java)
            startActivity(returnMyStoreintent)
        })
    }

    fun setViews() {
        binding.MenuCameraBtn.setOnClickListener {
            cameraPermission.launch(Manifest.permission.CAMERA)
        }
        binding.MenuGlyBtn.setOnClickListener {
            openGallery()
        }
    }

    fun openCamera() {
        val photoFile = File.createTempFile(
            "IMG_",
            ".jpg",
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        photoUri = FileProvider.getUriForFile(
            this,
            "${packageName}.provider",
            photoFile
        )

        cameraLauncher.launch(photoUri)
    }

    fun openGallery() {
        galleryLauncher.launch("image/*")
    }
}