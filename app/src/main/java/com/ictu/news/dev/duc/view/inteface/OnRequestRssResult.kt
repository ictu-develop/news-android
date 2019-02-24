package com.ictu.news.dev.duc.view.inteface

import com.ictu.news.dev.duc.collection.NewFeedCollection

interface OnRequestRssResult {
    fun onDone(newFeedCollection: NewFeedCollection)
    fun onFail(t: String)
}