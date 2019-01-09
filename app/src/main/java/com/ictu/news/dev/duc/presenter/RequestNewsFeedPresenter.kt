package com.ictu.news.dev.duc.presenter

import com.ictu.news.dev.duc.model.RequestNewsFeedModel
import com.ictu.news.dev.duc.view.inteface.RequestNewsfeedResult

class RequestNewsFeedPresenter (val requestNewsFeedResult: RequestNewsfeedResult) {

    private val requestNewsFeedModel by lazy {
        RequestNewsFeedModel(requestNewsFeedResult)
    }

    fun request(index: Int) {
        requestNewsFeedModel.request(index)
    }

}