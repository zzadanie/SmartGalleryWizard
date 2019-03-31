package com.example.smartgallerywizard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewSimpleImageAdapter(private val context: Context, private val itemsData: List<ImageDto>)
    : RecyclerView.Adapter<RecyclerViewSimpleImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_layout, parent, false) as View
        return ViewHolder(itemLayoutView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(context)
                .load(itemsData[position].link)
                .into(viewHolder.imageView)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        val imageView : ImageView = itemLayoutView.findViewById(gR.id.partialImageView)
    }

    override fun getItemCount(): Int {
        return itemsData.size
    }
}