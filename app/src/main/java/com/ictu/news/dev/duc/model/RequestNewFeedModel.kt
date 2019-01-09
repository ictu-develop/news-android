package com.ictu.news.dev.duc.model

import android.util.Log
import com.ictu.news.address.Address
import com.ictu.news.dev.duc.collection.ListNewFeedCollection
import com.ictu.news.dev.duc.view.inteface.RequestNewfeedResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestNewFeedModel(val requestNewsFeedResult: RequestNewfeedResult) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Address.domain)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService by lazy {
        retrofit.create(ApiInterface::class.java)
    }

    fun request(index: Int) {
        val call = apiService.loadNewsFeed(index.toString())

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