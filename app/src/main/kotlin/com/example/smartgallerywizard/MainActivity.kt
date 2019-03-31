package com.example.smartgallerywizard

import androidx.appcompat.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.view.View.*
import android.widget.Button
import khttp.get
import kotlinx.android.synthetic.main.activity_fullscreen.*
import org.json.JSONObject
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import java.util.stream.Collectors
import java.util.stream.IntStream

class MainActivity() : AppCompatActivity() {
    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) fullscreen_content.systemUiVisibility =
//                    SYSTEM_UI_FLAG_LOW_PROFILE or
//                            SYSTEM_UI_FLAG_FULLSCREEN or
//                            SYSTEM_UI_FLAG_LAYOUT_STABLE or
//                            SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
//                            SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
//                            SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        }
    }

    private lateinit var imagesList: List<Bitmap>
    private val uselessLink: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Wroclaw_kosciol_sw.Idziego_od_plKatedralnego.jpg/1280px-Wroclaw_kosciol_sw.Idziego_od_plKatedralnego.jpg"
    private var imagesDtoList: List<ImageDto> = listOf(ImageDto("titlee", date = "123333", tags = "#mleko", link = uselessLink))


    private fun createExemplaryImages() = listOf(createBitmap("a1.jpeg"), createBitmap("a2.jpeg"), createBitmap("a7.jpeg"), createBitmap("c13.jpeg"))

    private fun createBitmap(name: String) = BitmapFactory.decodeStream(assets.open(name))

    private val mShowPart2Runnable = Runnable {
        supportActionBar?.show()
//        fullscreen_content_controls.visibility = VISIBLE
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }
    private val mDelayHideTouchListener = OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var gridLayoutManager: androidx.recyclerview.widget.GridLayoutManager
    private lateinit var recyclerViewSimpleImageAdapter: RecyclerViewSimpleImageAdapter

//    private lateinit var recyclerViewAdapter : RecyclerView.Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mVisible = true
//        fullscreen_content.setOnClickListener { toggle() }
        main_click_button.setOnTouchListener(mDelayHideTouchListener)
        createListenerOnClickButton()

        val future = Executors.newSingleThreadExecutor().submit {
            val items = get(url = "https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=true")
                    .jsonObject.getJSONArray("items")
            val media = items.getJSONObject(0)["media"] as JSONObject
            val link = media["m"] as String


            val images = IntStream
                    .range(0, items.length())
                    .mapToObj<ImageDto> { i -> items.getJSONObject(i).toImageDto() }
                    .collect(Collectors.toList())

            imagesDtoList = images

//            val intent = Intent(this as AppCompatActivity, FullImageDisplayActivity::class.java)
//            intent.putExtra("IMAGE_URL", link)
//            startActivity(intent)


        }
//        recyclerViewSimpleImageAdapter = RecyclerViewSimpleImageAdapter(imagesDtoList)

        while (!future.isDone) {
        }

        imagesList = createExemplaryImages()

        recyclerView = findViewById(R.id.recyclerView)
        gridLayoutManager = androidx.recyclerview.widget.GridLayoutManager(applicationContext, 1)
        recyclerView.layoutManager = gridLayoutManager



        recyclerViewSimpleImageAdapter = RecyclerViewSimpleImageAdapter(applicationContext, imagesDtoList)
        recyclerView.adapter = recyclerViewSimpleImageAdapter

    }


    fun JSONObject.toImageDto(): ImageDto = ImageDto(title = getString("title"), date = getString("date_taken"), tags = getString("tags"), link = getJSONObject("media").getString("m"))


//        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView)


//        val uiHandler = Handler(Looper.getMainLooper())
//        uiHandler.post {
//            Picasso.get()
//                    .load("http://i.imgur.com/DvpvklR.png")
//                    .into(recyclerView)
//        }

//        val img : Bitmap = Picasso.get().load("http://i.imgur.com/DvpvklR.png").get()

    //        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)
    private fun loadImageFromURL(url: String): Drawable {
        val inputStream = URL(url).content as InputStream
        return Drawable.createFromStream(inputStream, "src name")
    }

    private val imgUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Wroclaw_kosciol_sw.Idziego_od_plKatedralnego.jpg/1280px-Wroclaw_kosciol_sw.Idziego_od_plKatedralnego.jpg"
//    private val image = loadImageFromURL(imgUrl)
//
//    private fun parseImg(imgUrl: String) {
//        val handler = Handler(Looper.getMainLooper())
//        handler.post {
//            val url = URL(imgUrl)
//            val createBitmap: Bitmap = Picasso.get().load("http://i.imgur.com/DvpvklR.png").get()
//        }
//    }
//}

    private var bitmap: Bitmap? = null

    private fun createListenerOnClickButton() {
        val clickButton = findViewById<Button>(R.id.main_click_button)
        clickButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this@MainActivity).create()

            val d = Drawable.createFromStream(assets.open("a1.jpeg"), null)


//        alertDialog.setTitle("DOES IMG EXIST?")
//        alertDialog.setFeatureDrawable(123451, BitmapDrawable(resources, parseImg(imgUrl)))
//        alertDialog.setMessage("Alert message to be shown")
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
//        alertDialog.show()


            recyclerView.adapter = RecyclerViewColumnedImageAdapter(applicationContext, imagesDtoList)

            gridLayoutManager.spanCount = gridLayoutManager.spanCount % 2 + 1
            // TODO, MOVE TO onImageViewClick
//            val intent = Intent(this as AppCompatActivity, FullImageDisplayActivity::class.java)
//            intent.putExtra("IMAGE_URL", imgUrl)
//
//            startActivity(intent)

            //            this@MainActivity.startActivity(Intent(this@MainActivity,
//                    SecondActivity::class.java))
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        delayedHide(100)
    }

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        supportActionBar?.hide()
//    fullscreen_content_controls.visibility = GONE
        mVisible = false
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
//    fullscreen_content.systemUiVisibility =
//            SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
//                    SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        mVisible = true
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    companion object {
        private val AUTO_HIDE = true
        private val AUTO_HIDE_DELAY_MILLIS = 3000
        private val UI_ANIMATION_DELAY = 300
    }
}
