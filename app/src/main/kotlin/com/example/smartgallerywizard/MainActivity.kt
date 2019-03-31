package com.example.smartgallerywizard

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import khttp.get
import org.json.JSONObject
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import java.util.stream.Collectors
import java.util.stream.IntStream

class MainActivity : AppCompatActivity() {
    private lateinit var imagesDtoList: List<ImageDto>
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var gridLayoutManager: androidx.recyclerview.widget.GridLayoutManager
    private lateinit var recyclerViewSimpleImageAdapter: RecyclerViewSimpleImageAdapter

//    private lateinit var recyclerViewAdapter : RecyclerView.Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        createListenerOnClickButton()

        gridLayoutManager = GridLayoutManager(applicationContext, 1)

        val future = Executors.newSingleThreadExecutor().submit {
            val items = get(url = "https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=true")
                    .jsonObject.getJSONArray("items")

            val images = IntStream.range(0, items.length())
                    .mapToObj<ImageDto> { i -> items.getJSONObject(i).toImageDto() }
                    .collect(Collectors.toList())

            imagesDtoList = images
        }
//        recyclerViewSimpleImageAdapter = RecyclerViewSimpleImageAdapter(imagesDtoList)
        while (!future.isDone) {
        }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = gridLayoutManager
        recyclerViewSimpleImageAdapter = RecyclerViewSimpleImageAdapter(applicationContext, imagesDtoList)
        recyclerView.adapter = RecyclerViewSimpleImageAdapter(applicationContext, imagesDtoList)
    }

    fun JSONObject.toImageDto(): ImageDto = ImageDto(title = getString("title"), date = getString("date_taken"), tags = getString("tags"), link = getJSONObject("media").getString("m"))

    private fun loadImageFromURL(url: String): Drawable {
        val inputStream = URL(url).content as InputStream
        return Drawable.createFromStream(inputStream, "src name")
    }

    private fun createListenerOnClickButton() {
        val clickButton = findViewById<Button>(R.id.main_click_button)
        clickButton.setOnClickListener {

            val spanCount = gridLayoutManager.spanCount
            if (spanCount == 2) {
                recyclerView.adapter = RecyclerViewSimpleImageAdapter(applicationContext, imagesDtoList)
            } else {
                recyclerView.adapter = RecyclerViewColumnedImageAdapter(applicationContext, imagesDtoList)
            }

            gridLayoutManager.spanCount = gridLayoutManager.spanCount % 2 + 1
        }
    }

}
