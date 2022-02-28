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
import com.example.cap.databinding.ActivityStore2Binding
import com.example.cap.dataclass.Data
import com.example.cap.dataclass.FoodListDto
import com.example.cap.retrofit2.APIstore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Store2 : AppCompatActivity() {


    val binding by lazy { ActivityStore2Binding.inflate(layoutInflater)}

    var photoUri:Uri? = null
    lateinit var cameraPermission:ActivityResultLauncher<String>
    lateinit var storagePermission:ActivityResultLauncher<String>

    lateinit var cameraLauncher:ActivityResultLauncher<Uri>
    lateinit var galleryLauncher:ActivityResultLauncher<String>

    var foodarray = emptyArray<FoodListDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://da86-125-180-55-163.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(APIstore::class.java)

        storagePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if(isGranted) {
                setViews()
            } else {
                Toast.makeText(baseContext, "외부저장소 권한을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_LONG).show()
                finish()
            }
        }

        cameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if(isGranted) {
                openCamera()
            } else {
                Toast.makeText(baseContext, "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.", Toast.LENGTH_LONG).show()
            }
        }

        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess  ->
            if(isSuccess) { binding.storeImg.setImageURI(photoUri) }
        }

        galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding.storeImg.setImageURI(uri)
        }

        storagePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        binding.sampleBtn.setOnClickListener(){

            val MyStoreintent = Intent(this, MyStore::class.java)
            startActivity(MyStoreintent)
        }

        binding.signUpBtn2.setOnClickListener({
           val storedata =
               Data(
                   foodarray,
                   binding.storeNameIp.text.toString(),
                   binding.storeNumIp.text.toString(),
                   binding.onerNumIp.text.toString(),
                   binding.toOriginIp.text.toString(),
                   binding.storeIntroIp.text.toString(),
               )



            retrofitService.post_users(storedata).enqueue(object : Callback <Data> {
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    Log.d("Store",response.toString())
                    Log.d("Store", response.body().toString())
                    if(!response.body().toString().isEmpty()){
                        binding.postText.setText(response.body().toString())
                 }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    Log.d("Store",t.message.toString())
                    Log.d("Store","스토어정보 통신실패")
                }

            })



            val MyStoreintent = Intent(this, MyStore::class.java)
            startActivity(MyStoreintent)


        })

    }



    // 메소드
    fun setViews() {
        binding.CameraBtn.setOnClickListener {
            cameraPermission.launch(Manifest.permission.CAMERA)
        }
        binding.GlyBtn.setOnClickListener {
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

