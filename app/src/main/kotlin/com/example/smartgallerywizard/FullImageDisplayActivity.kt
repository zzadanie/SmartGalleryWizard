package com.example.smartgallerywizard


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView

class FullImageDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        print("abbaaa")

        setContentView(R.layout.full_image_layout)
        val imageView = findViewById<ImageView>(R.id.imageView)




        val drawable : Drawable = Drawable.createFromStream(assets.open("a1.jpeg"), null)

//        imageView.setImageResource(R.drawable.notification_bg_low)

        imageView.setImageDrawable(drawable)
    }
}
