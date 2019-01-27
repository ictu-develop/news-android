package com.ictu.news.dev.duc.presenter

import com.ictu.news.dev.duc.model.RequestNewFeedModel
import com.ictu.news.dev.duc.view.inteface.OnRequestRssResult

class RequestNewFeedPresenter (private val requestRssResult: OnRequestRssResult) {

    private val requestNewFeedModel by lazy {
        RequestNewFeedModel(requestRssResult)
    }

    fun request(index: Int) {
        requestNewFeedModel.request(index)
    }

}