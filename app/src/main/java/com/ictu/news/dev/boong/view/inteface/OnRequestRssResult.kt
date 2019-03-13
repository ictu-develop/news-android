package com.ictu.news.dev.boong.view.inteface

import com.ictu.news.dev.boong.collection.NewFeedCollection

interface OnRequestRssResult {
    fun onDone(newFeedCollection: NewFeedCollection)
    fun onFail(t: String)
}