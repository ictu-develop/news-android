package com.ictu.news.dev.duc.model

import android.util.Log
import com.ictu.news.dev.duc.collection.NewFeedCollection
import com.ictu.news.dev.duc.view.inteface.OnRequestSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RequestSearchModel(val onRequestSearchResult: OnRequestSearchResult) {

    lateinit var call: Call<NewFeedCollection>

    fun request(keyword: String) {
        call = RetrofitCommon.apiService.loadSearch(keyword)

        call.enqueue(object : Callback<NewFeedCollection> {
            override fun onFailure(call: Call<NewFeedCollection>, t: Throwable) {
                Log.d("Error_request", "$t")
            }

            override fun onResponse(call: Call<NewFeedCollection>, response: Response<NewFeedCollection>) {
                val body = response.body()
                body?.let {
                    val code = it.code
                    if (code == 200)
                        onRequestSearchResult.onDone(it)
                    else if (code == 204 && it.post.isEmpty())
                        onRequestSearchResult.onEmpty(it)
                } ?: run {
                    onRequestSearchResult.onFail("")
                }
            }

        })
    }

}