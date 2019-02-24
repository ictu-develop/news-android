package com.ictu.news.dev.duc.view.inteface

import com.ictu.news.dev.duc.collection.NewFeedCollection

interface OnRequestSearchResult {
    fun onDone(result: NewFeedCollection)
    fun onEmpty(result: NewFeedCollection)
    fun onFail(t: String)
}