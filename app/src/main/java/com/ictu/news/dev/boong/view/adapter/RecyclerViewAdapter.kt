package com.ictu.news.dev.boong.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ictu.news.R
import com.ictu.news.dev.boong.collection.ItemNewFeedCollection
import com.ictu.news.dev.boong.view.inteface.OnRecyclerViewItemClickListener
import kotlinx.android.synthetic.main.item_first_post.view.*
import kotlinx.android.synthetic.main.item_post.view.*

class RecyclerViewAdapter(private val context: Context, private val collectionItem: ArrayList<ItemNewFeedCollection?>,
                          private val recyclerViewItemClickListener: OnRecyclerViewItemClickListener ) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    //ViewHolder, this class will hold each item
    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        var view: View? = null

        // First item: item_first_post

        if (p1 == 0)
            view = layoutInflater.inflate(R.layout.item_first_post, p0, false)
        if (p1 == 1)
            view = layoutInflater.inflate(R.layout.item_loading, p0, false)
        if (p1 == 2)
            view = layoutInflater.inflate(R.layout.item_post, p0, false)

        return MyViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return collectionItem.size
    }

    // Use this to know first item
    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> 0
            collectionItem[position] == null -> 1
            else -> 2
        }
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val data = collectionItem[p1]
        val view = p0.itemView

        if (data != null) {
            // If first item
            if (p1 == 0)
                createContentFirstPost(data, view)
            // if not first item
            if (p1 > 0)
                createContentPost(data, view)

            // Call event
            view.setOnClickListener {
                recyclerViewItemClickListener.onItemClick(view, p1)
            }
        }

    }

    private fun createContentFirstPost(data: ItemNewFeedCollection, view: View) {
        Glide
            .with(context)
            .load(data.image)
            .into(view.image_preview_first_post)

        view.title_first_post.text = data.post_name
        view.description_first_post.text = data.description
    }

    private fun createContentPost(data: ItemNewFeedCollection, view: View) {
        Glide
            .with(context)
            .load(data.image)
            .into(view.image_preview_post)

        view.title_post.text = data.post_name
    }
}