package com.example.smartgallerywizard

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartgallerywizard.adapter.RecyclerViewColumnedImageAdapter
import com.example.smartgallerywizard.adapter.RecyclerViewSimpleImageAdapter
import com.example.smartgallerywizard.dto.ImageDto
import khttp.get
import org.json.JSONObject
import java.util.concurrent.Executors
import java.util.stream.Collectors
import java.util.stream.IntStream


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var gridLayoutManager: androidx.recyclerview.widget.GridLayoutManager
    private lateinit var recyclerViewSimpleImageAdapter: RecyclerViewSimpleImageAdapter
    private lateinit var toolbar : Toolbar
    private var imagesDtoList: List<ImageDto> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        gridLayoutManager = GridLayoutManager(applicationContext, 1)

        val future = Executors.newSingleThreadExecutor().submit {
            val items = get(url = "https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=true")
                    .jsonObject.getJSONArray("items")

            val images = IntStream.range(0, items.length())
                    .mapToObj<ImageDto> { i -> items.getJSONObject(i).toImageDto() }
                    .collect(Collectors.toList())

            imagesDtoList = images
        }
        while (!future.isDone);
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = gridLayoutManager
        recyclerViewSimpleImageAdapter = RecyclerViewSimpleImageAdapter(applicationContext, imagesDtoList)
        recyclerView.adapter = RecyclerViewSimpleImageAdapter(applicationContext, imagesDtoList)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = applicationContext.applicationInfo.loadLabel(applicationContext.packageManager)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    fun JSONObject.toImageDto(): ImageDto = ImageDto(title = getString("title"), date = getString("date_taken"), tags = getString("tags"), link = getJSONObject("media").getString("m"))

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        if(item.itemId == R.id.gridView) {
            gridLayoutManager.spanCount = 1
            recyclerView.adapter = RecyclerViewSimpleImageAdapter(applicationContext, imagesDtoList)
        }
        else if(item.itemId == R.id.detailedView) {
            gridLayoutManager.spanCount = 2
            recyclerView.adapter = RecyclerViewColumnedImageAdapter(applicationContext, imagesDtoList)
        }
        return true
    }
}
