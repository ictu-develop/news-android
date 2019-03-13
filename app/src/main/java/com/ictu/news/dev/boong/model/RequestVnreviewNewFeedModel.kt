package com.ictu.news.dev.boong.model

import android.util.Log
import com.ictu.news.dev.boong.collection.NewFeedCollection
import com.ictu.news.dev.boong.view.inteface.OnRequestRssResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestVnreviewNewFeedModel(private val requestRssResult: OnRequestRssResult) {

    lateinit var call: Call<NewFeedCollection>

    // Request to Vnreview Newfeed
    fun request(index: Int) {
        call = RetrofitCommon.apiService.loadVnreviewNewFeed(index.toString())

        call.enqueue(object : Callback<NewFeedCollection> {
            override fun onResponse(call: Call<NewFeedCollection>, response: Response<NewFeedCollection>) {
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

            override fun onFailure(call: Call<NewFeedCollection>, t: Throwable) {
                Log.d("Error_request", "$t")
            }
        })
    }

}