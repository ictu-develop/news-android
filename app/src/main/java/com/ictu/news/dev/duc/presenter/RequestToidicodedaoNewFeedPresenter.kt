package com.ictu.news.dev.duc.presenter

import com.ictu.news.dev.duc.model.RequestToidicodedaoNewFeedModel
import com.ictu.news.dev.duc.view.inteface.OnRequestResult


class RequestToidicodedaoNewFeedPresenter(private val requestResult: OnRequestResult) {

    private val requestToidicodedaoNewFeedModel by lazy {
        RequestToidicodedaoNewFeedModel(requestResult)
    }

    fun request(index: Int) {
        requestToidicodedaoNewFeedModel.request(index)
    }

}