package com.ictu.news.dev.boong.model

import com.ictu.news.dev.boong.collection.PostContentCollection
import com.ictu.news.dev.boong.view.interface_.OnRequestPostContentResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestPostContent(val onRequestPostContentResult: OnRequestPostContentResult) {

    lateinit var call: Call<PostContentCollection>

    // Request to post content
    fun request(source: String, link: String) {
        when (source) {
            "vnreview" -> {
                call = RetrofitCommon.apiService.loadPostContentVnreview(link)
            }
            "toidicodedao" -> {
                call = RetrofitCommon.apiService.loadPostContentToidicodedao(link)
            }
        }

        call.enqueue(object: Callback<PostContentCollection> {
            override fun onFailure(call: Call<PostContentCollection>, t: Throwable) {
                onRequestPostContentResult.onFail(t.toString())
            }

            override fun onResponse(call: Call<PostContentCollection>, response: Response<PostContentCollection>) {
                val content = response.body()
                content?.let {
                    if (it.code == 200)
                        onRequestPostContentResult.onDone(it)
                    else
                        onRequestPostContentResult.onFail("code: ${it.code}")
                } ?: run {
                    onRequestPostContentResult.onFail("")
                }
            }

        })

    }

}