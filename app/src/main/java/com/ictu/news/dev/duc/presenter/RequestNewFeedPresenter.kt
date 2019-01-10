package com.ictu.news.dev.duc.presenter

import com.ictu.news.dev.duc.model.RequestNewFeedModel
import com.ictu.news.dev.duc.view.inteface.OnRequestNewFeedResult

class RequestNewFeedPresenter (val requestNewsFeedResult: OnRequestNewFeedResult) {

    private val requestNewFeedModel by lazy {
        RequestNewFeedModel(requestNewsFeedResult)
    }

    fun request(index: Int) {
        requestNewFeedModel.request(index)
    }

}