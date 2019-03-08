package com.ictu.news.dev.boong.view.inteface

import android.view.View

interface OnRecyclerViewItemClickListener {
    fun onItemClick(view: View, postion: Int)
    fun onLongItemClick(view: View, postion: Int)
}