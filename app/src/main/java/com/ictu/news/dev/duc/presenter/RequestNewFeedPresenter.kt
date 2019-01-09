package com.ictu.news.dev.duc.presenter

import com.ictu.news.dev.duc.model.RequestNewFeedModel
import com.ictu.news.dev.duc.view.inteface.RequestNewfeedResult

class RequestNewFeedPresenter (val requestNewsFeedResult: RequestNewfeedResult) {

    private val requestNewFeedModel by lazy {
        RequestNewFeedModel(requestNewsFeedResult)
    }

    fun request(index: Int) {
        requestNewFeedModel.request(index)
    }

}