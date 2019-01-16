package com.ictu.news.dev.duc.presenter

import com.ictu.news.dev.duc.model.RequestVnreviewNewFeedModel
import com.ictu.news.dev.duc.view.inteface.OnRequestResult

class RequestVnreviewNewFeedPresenter(private val requestResult: OnRequestResult) {

    private val requestVnreviewNewFeedModel by lazy {
        RequestVnreviewNewFeedModel(requestResult)
    }

    fun request(index: Int) {
        requestVnreviewNewFeedModel.request(index)
    }
}