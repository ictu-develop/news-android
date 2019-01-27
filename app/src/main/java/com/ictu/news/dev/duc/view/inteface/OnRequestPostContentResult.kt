package com.ictu.news.dev.duc.view.inteface

import com.ictu.news.dev.duc.collection.PostContentCollection

interface OnRequestPostContentResult {
    fun onDone(postContentResult: PostContentCollection)
    fun onFail(t: String)
}