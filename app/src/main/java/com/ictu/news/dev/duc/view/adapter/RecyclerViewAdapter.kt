package com.ictu.news.dev.duc.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ictu.news.R
import com.ictu.news.dev.duc.collection.NewFeedCollection
import kotlinx.android.synthetic.main.item_first_post.view.*
import kotlinx.android.synthetic.main.item_post.view.*

class RecyclerViewAdapter(val context: Context, val collection: ArrayList<NewFeedCollection>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    //ViewHolder, this class will hold each item
    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        val view = if (p1 == 0)
            layoutInflater.inflate(R.layout.item_first_post, p0, false)
        else
            layoutInflater.inflate(R.layout.item_post, p0, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return collection.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val data = collection[p1]
        val view = p0.itemView

        if (p1 == 0) {
            Glide
                .with(context)
                .load(data.image)
                .into(view.image_preview_first_post)

            view.title_first_post.text = data.postName
            view.description_first_post.text = data.description
        } else {
            Glide
                .with(context)
                .load(data.image)
                .into(view.image_preview_post)

            view.title_post.text = data.postName
        }
    }

}