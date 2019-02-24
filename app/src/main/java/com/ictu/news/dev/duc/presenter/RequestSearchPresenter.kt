package com.ictu.news.dev.duc.presenter

import com.ictu.news.dev.duc.model.RequestSearchModel
import com.ictu.news.dev.duc.view.inteface.OnRequestSearchResult

class RequestSearchPresenter(private val onRequestSearchResult: OnRequestSearchResult) {

    private val requestSearchModel by lazy {
        RequestSearchModel(onRequestSearchResult)
    }

    fun search(keyword: String) {
        requestSearchModel.request(keyword)
    }

}