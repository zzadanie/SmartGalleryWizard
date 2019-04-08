package com.example.smartgallerywizard.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartgallerywizard.R
import com.example.smartgallerywizard.activity.FullImageDisplayActivity
import com.example.smartgallerywizard.dto.ImageDto

class RecyclerViewSimpleImageAdapter(private val applicationContext: Context, private val itemsData: List<ImageDto>)
    : RecyclerView.Adapter<RecyclerViewSimpleImageAdapter.ViewHolder>() {
    override fun getItemCount(): Int = itemsData.size

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_layout, parent, false) as View
        return ViewHolder(itemLayoutView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(applicationContext)
                .load(itemsData[position].link)
                .into(viewHolder.imageView)
        viewHolder.photoDateTextView.text=itemsData[position].date
        viewHolder.photoTagsTextView.text=itemsData[position].tags
        viewHolder.photoTitleTextView.text=itemsData[position].title
    }

    inner class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {
        val imageView: ImageView
        val photoTagsTextView : TextView
        val photoTitleTextView : TextView
        val photoDateTextView : TextView

        constructor(itemLayoutView: View) : super(itemLayoutView) {
            imageView = itemLayoutView.findViewById(R.id.partialImageView)
            photoTagsTextView = itemLayoutView.findViewById(R.id.photoTags)
            photoTitleTextView = itemLayoutView.findViewById(R.id.photoTitle)
            photoDateTextView = itemLayoutView.findViewById(R.id.photoDate)
            itemLayoutView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val link = itemsData[position].link
                val intent = Intent(applicationContext, FullImageDisplayActivity::class.java)
                intent.putExtra("IMAGE_URL", link)
                startActivity(applicationContext, intent, null)
            }
        }
    }

}