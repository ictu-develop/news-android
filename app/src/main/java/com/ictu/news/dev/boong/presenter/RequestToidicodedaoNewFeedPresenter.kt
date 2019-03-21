package com.ictu.news.dev.boong.presenter

import com.ictu.news.dev.boong.model.RequestToidicodedaoNewFeedModel
import com.ictu.news.dev.boong.view.interface_.OnRequestRssResult


class RequestToidicodedaoNewFeedPresenter(private val requestRssResult: OnRequestRssResult) {

    private val requestToidicodedaoNewFeedModel = RequestToidicodedaoNewFeedModel(requestRssResult)

    fun request(index: Int) {
        requestToidicodedaoNewFeedModel.request(index)
    }

}