package com.example.smartgallerywizard


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.squareup.picasso.Picasso
import uk.co.senab.photoview.PhotoViewAttacher
import java.net.URL
import java.util.concurrent.Executors

class FullImageDisplayActivity : AppCompatActivity() {

    private lateinit var photoViewAttacher: PhotoViewAttacher

    private fun defaultImage() = BitmapFactory.decodeStream(assets.open("a7.jpeg"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_image_layout)
        val imageView = findViewById<ImageView>(R.id.imageView)
        photoViewAttacher = PhotoViewAttacher(imageView)

//        val imageUrl = intent.getStringExtra("IMAGE")
//        var bitmap: Bitmap? = null
//        if (imageUrl != null) {
//            val submit = Executors.newSingleThreadExecutor().submit { BitmapFactory.decodeStream(URL(imageUrl).openStream()) }
//            while (!submit.isDone) {
//
//            }
//            var submitGet = submit.get() as Bitmap
////                    bitmap = BitmapFactory . decodeStream (URL(imageUrl).openStream())
//        }

        val imgUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/8731viki_Politechnika_Wroc%C5%82awska_ul._Wybrze%C5%BCe_Wyspia%C5%84skiego._Foto_Barbara_Maliszewska.jpg/1280px-8731viki_Politechnika_Wroc%C5%82awska_ul._Wybrze%C5%BCe_Wyspia%C5%84skiego._Foto_Barbara_Maliszewska.jpg"


//        Picasso.get().load(imgUrl).into(imgUrl)


        imageView.setImageBitmap(defaultImage())



    }


}
