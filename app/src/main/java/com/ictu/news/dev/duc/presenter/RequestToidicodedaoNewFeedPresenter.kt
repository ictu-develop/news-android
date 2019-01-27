package com.ictu.news.dev.duc.presenter

import com.ictu.news.dev.duc.model.RequestToidicodedaoNewFeedModel
import com.ictu.news.dev.duc.view.inteface.OnRequestRssResult


class RequestToidicodedaoNewFeedPresenter(private val requestRssResult: OnRequestRssResult) {

    private val requestToidicodedaoNewFeedModel by lazy {
        RequestToidicodedaoNewFeedModel(requestRssResult)
    }

    fun request(index: Int) {
        requestToidicodedaoNewFeedModel.request(index)
    }

}