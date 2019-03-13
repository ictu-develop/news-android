package com.ictu.news.dev.boong.view.interface_

import com.ictu.news.dev.boong.collection.NewFeedCollection

interface OnRequestSearchResult {
    fun onDone(result: NewFeedCollection)
    fun onEmpty(result: NewFeedCollection)
    fun onFail(t: String)
}