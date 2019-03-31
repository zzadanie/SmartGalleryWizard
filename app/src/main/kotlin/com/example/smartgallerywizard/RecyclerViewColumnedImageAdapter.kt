package com.example.smartgallerywizard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewColumnedImageAdapter(private val applicationContext: Context, private val itemsData: List<ImageDto>) : RecyclerView.Adapter<RecyclerViewColumnedImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_two_columns_layout, parent, false)
        return ViewHolder(itemLayoutView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(applicationContext)
                .load(itemsData[position].link)
                .into(viewHolder.imageView)
    }

    override fun getItemCount(): Int {
        return itemsData.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {
        val imageView: ImageView

        constructor(itemLayoutView: View) : super(itemLayoutView) {
            imageView = itemLayoutView.findViewById(R.id.halfImageView)
            itemLayoutView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val link = itemsData[position].link
                val intent = Intent(applicationContext, FullImageDisplayActivity::class.java)
                intent.putExtra("IMAGE_URL", link)
                ContextCompat.startActivity(applicationContext, intent, null)
            }
        }
    }
}