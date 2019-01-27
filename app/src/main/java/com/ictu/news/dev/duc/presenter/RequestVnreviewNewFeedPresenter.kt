package com.ictu.news.dev.duc.presenter

import com.ictu.news.dev.duc.model.RequestVnreviewNewFeedModel
import com.ictu.news.dev.duc.view.inteface.OnRequestRssResult

class RequestVnreviewNewFeedPresenter(private val requestRssResult: OnRequestRssResult) {

    private val requestVnreviewNewFeedModel by lazy {
        RequestVnreviewNewFeedModel(requestRssResult)
    }

    fun request(index: Int) {
        requestVnreviewNewFeedModel.request(index)
    }
}