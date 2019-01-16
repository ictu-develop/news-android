package com.ictu.news.dev.duc.presenter

import com.ictu.news.dev.duc.model.RequestNewFeedModel
import com.ictu.news.dev.duc.view.inteface.OnRequestResult

class RequestNewFeedPresenter (private val requestResult: OnRequestResult) {

    private val requestNewFeedModel by lazy {
        RequestNewFeedModel(requestResult)
    }

    fun request(index: Int) {
        requestNewFeedModel.request(index)
    }

}