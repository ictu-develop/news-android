package com.ictu.news.dev.boong.presenter

import com.ictu.news.dev.boong.model.RequestSearchModel
import com.ictu.news.dev.boong.view.interface_.OnRequestSearchResult

class RequestSearchPresenter(private val onRequestSearchResult: OnRequestSearchResult) {

    private val requestSearchModel = RequestSearchModel(onRequestSearchResult)


    fun search(keyword: String) {
        requestSearchModel.request(keyword)
    }

}