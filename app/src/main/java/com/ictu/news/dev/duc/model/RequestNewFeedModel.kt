package com.ictu.news.dev.duc.model

import android.util.Log
import com.ictu.news.dev.duc.collection.ListNewFeedCollection
import com.ictu.news.dev.duc.view.inteface.OnRequestNewFeedResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestNewFeedModel(val requestNewsFeedResult: OnRequestNewFeedResult) {

    // Request to NewFeed
    fun request(index: Int) {
        val call = RetrofitCommon.apiService.loadNewFeed(index.toString())

        call.enqueue(object : Callback<ListNewFeedCollection> {
            override fun onResponse(call: Call<ListNewFeedCollection>, response: Response<ListNewFeedCollection>) {
                val newsFeedList = response.body()
                newsFeedList?.let {
                    requestNewsFeedResult.onDone(newsFeedList)
                } ?: run {
                    requestNewsFeedResult.onFail()
                }
            }

            override fun onFailure(call: Call<ListNewFeedCollection>, t: Throwable) {
                Log.d("Error_request", "$t")
            }
        })
    }

}