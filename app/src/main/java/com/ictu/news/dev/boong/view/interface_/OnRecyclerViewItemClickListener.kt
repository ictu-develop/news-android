package com.ictu.news.dev.boong.view.interface_

import android.view.View

interface OnRecyclerViewItemClickListener {
    fun onItemClick(view: View, postion: Int)
    fun onLongItemClick(view: View, postion: Int)
}