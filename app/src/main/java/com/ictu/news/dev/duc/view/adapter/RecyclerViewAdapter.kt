package com.ictu.news.dev.duc.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ictu.news.R
import com.ictu.news.dev.duc.collection.NewFeedCollection
import com.ictu.news.dev.duc.view.inteface.OnRecyclerViewItemClickListener
import kotlinx.android.synthetic.main.item_first_post.view.*
import kotlinx.android.synthetic.main.item_post.view.*

class RecyclerViewAdapter(private val context: Context, private val collection: ArrayList<NewFeedCollection>,
                          private val recyclerViewItemClickListener: OnRecyclerViewItemClickListener ) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    //ViewHolder, this class will hold each item
    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        val view: View

        // First item: item_first_post
        view = if (p1 == 1)
            layoutInflater.inflate(R.layout.item_first_post, p0, false)
        else
            layoutInflater.inflate(R.layout.item_post, p0, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return collection.size
    }

    // Use this to know first item
    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> 1
            position < collection.size - 1 -> 2
            else -> 3
        }
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val data = collection[p1]
        val view = p0.itemView

        if (data.date == "null" && data.date == data.description && data.date == data.full_post && data.date == data.image
        && data.date == data.full_post) {
            view.normal_layout.visibility = GONE
            view.item_loading.visibility = VISIBLE
        } else {
            // If first item
            if (p1 == 0)
                createContentFirstPost(data, view)

            // if not first item
            if (p1 > 0)
                createContentPost(data, view)
        }


        // Call event
        view.setOnClickListener {
            recyclerViewItemClickListener.onItemClick(view, p1)
        }

    }

    private fun createContentFirstPost(data: NewFeedCollection, view: View) {
        Glide
            .with(context)
            .load(data.image)
            .into(view.image_preview_first_post)

        view.title_first_post.text = data.post_name
        view.description_first_post.text = data.description
    }

    private fun createContentPost(data: NewFeedCollection, view: View) {
        Glide
            .with(context)
            .load(data.image)
            .into(view.image_preview_post)

        view.title_post.text = data.post_name
    }

}