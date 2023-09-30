package com.coding.captureimage

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var captureIV : ImageView
    private lateinit var imageUrl : Uri

    private val contract = registerForActivityResult(ActivityResultContracts.TakePicture()){

        captureIV.setImageURI(null)
        captureIV.setImageURI(imageUrl)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageUrl = createImageUri()
        captureIV = findViewById(R.id.captureImageView)
        val captureImgBtn = findViewById<Button>(R.id.captureImgBtn)
        captureImgBtn.setOnClickListener {
            contract.launch(imageUrl)
        }

    }
    private fun createImageUri():Uri {
        val image = File(filesDir,"camera_photos.png")
        return FileProvider.getUriForFile(this,
            "com.coding.captureimage.FileProvider",
            image)
    }
}