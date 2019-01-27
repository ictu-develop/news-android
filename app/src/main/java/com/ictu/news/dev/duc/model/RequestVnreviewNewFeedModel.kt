package com.ictu.news.dev.duc.model

import android.util.Log
import com.ictu.news.dev.duc.collection.ListNewFeedCollection
import com.ictu.news.dev.duc.view.inteface.OnRequestRssResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestVnreviewNewFeedModel(private val requestRssResult: OnRequestRssResult) {

    lateinit var call: Call<ListNewFeedCollection>

    // Request to Vnreview Newfeed
    fun request(index: Int) {
        call = RetrofitCommon.apiService.loadVnreviewNewFeed(index.toString())

        call.enqueue(object : Callback<ListNewFeedCollection> {
            override fun onResponse(call: Call<ListNewFeedCollection>, response: Response<ListNewFeedCollection>) {
                val newsFeedList = response.body()
                newsFeedList?.let {
                    if (it.code == 200)
                        requestRssResult.onDone(newsFeedList)
                    else
                        requestRssResult.onFail("code: ${it.code}")
                } ?: run {
                    requestRssResult.onFail("")
                }
            }

            override fun onFailure(call: Call<ListNewFeedCollection>, t: Throwable) {
                Log.d("Error_request", "$t")
            }
        })
    }

}