package com.ictu.news.dev.boong.presenter

import com.ictu.news.dev.boong.model.RequestVnreviewNewFeedModel
import com.ictu.news.dev.boong.view.interface_.OnRequestRssResult

class RequestVnreviewNewFeedPresenter(private val requestRssResult: OnRequestRssResult) {

    private val requestVnreviewNewFeedModel = RequestVnreviewNewFeedModel(requestRssResult)

    fun request(index: Int) {
        requestVnreviewNewFeedModel.request(index)
    }
}