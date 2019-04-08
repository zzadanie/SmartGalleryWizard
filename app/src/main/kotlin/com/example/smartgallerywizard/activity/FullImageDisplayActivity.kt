package com.example.smartgallerywizard.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.smartgallerywizard.R
import com.github.chrisbanes.photoview.PhotoViewAttacher

class FullImageDisplayActivity : AppCompatActivity() {

    private lateinit var photoViewAttacher: PhotoViewAttacher

    private fun defaultImage() = BitmapFactory.decodeStream(assets.open("a1.jpeg"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_image_layout)
        val imageView = findViewById<ImageView>(R.id.imageView)
        this.photoViewAttacher = PhotoViewAttacher(imageView)

        val imgUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/8731viki_Politechnika_Wroc%C5%82awska_ul._Wybrze%C5%BCe_Wyspia%C5%84skiego._Foto_Barbara_Maliszewska.jpg/1280px-8731viki_Politechnika_Wroc%C5%82awska_ul._Wybrze%C5%BCe_Wyspia%C5%84skiego._Foto_Barbara_Maliszewska.jpg"

        val imageUrl = intent.getStringExtra("IMAGE_URL") ?: imgUrl
        Glide.with(applicationContext)
                .load(imageUrl)
                .into(imageView)
//        imageView.setImageBitmap(defaultImage()) //check normal download by threads or khttp
    }
}
