package com.ictu.news.dev.duc.view.inteface

import com.ictu.news.dev.duc.collection.ListNewFeedCollection

interface OnRequestNewFeedResult {
    fun onDone(newFeedCollection: ListNewFeedCollection)
    fun onFail()
}