package com.ictu.news.dev.duc.model

import com.ictu.news.dev.duc.collection.ListNewFeedCollection
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("newsfeed")
    fun loadNewsFeed(@Query("page") index: String): Call<ListNewFeedCollection>
}