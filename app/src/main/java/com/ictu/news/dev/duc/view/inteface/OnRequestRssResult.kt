package com.ictu.news.dev.duc.view.inteface

import com.ictu.news.dev.duc.collection.ListNewFeedCollection

interface OnRequestRssResult {
    fun onDone(newFeedCollection: ListNewFeedCollection)
    fun onFail(t: String)
}