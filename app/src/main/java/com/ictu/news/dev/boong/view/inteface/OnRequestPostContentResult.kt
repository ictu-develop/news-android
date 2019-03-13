package com.ictu.news.dev.boong.view.inteface

import com.ictu.news.dev.boong.collection.PostContentCollection

interface OnRequestPostContentResult {
    fun onDone(postContentResult: PostContentCollection)
    fun onFail(t: String)
}