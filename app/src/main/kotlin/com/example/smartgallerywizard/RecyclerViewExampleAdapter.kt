package com.example.smartgallerywizard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewExampleAdapter(private val context: Context, private val itemsData: List<ImageDto>)
    : RecyclerView.Adapter<RecyclerViewExampleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_layout, parent, false) as View
        return ViewHolder(itemLayoutView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(context)
                .load(itemsData[position])
                .into(viewHolder.imageView)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {

        lateinit var imageView : ImageView
//        var txtViewTitle: TextView
//        var imgViewIcon: ImageView
//
//        init {
//            txtViewTitle = itemLayoutView.findViewById(R.id.title)
//            imgViewIcon = itemLayoutView.findViewById(R.id.icon2) as ImageView
//        }
    }

    override fun getItemCount(): Int {
        return itemsData.size
    }
}