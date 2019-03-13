package com.ictu.news.dev.boong.presenter

import com.ictu.news.dev.boong.model.RequestNewFeedModel
import com.ictu.news.dev.boong.view.interface_.OnRequestRssResult

class RequestNewFeedPresenter (private val requestRssResult: OnRequestRssResult) {

    private val requestNewFeedModel by lazy {
        RequestNewFeedModel(requestRssResult)
    }

    fun request(index: Int) {
        requestNewFeedModel.request(index)
    }

}