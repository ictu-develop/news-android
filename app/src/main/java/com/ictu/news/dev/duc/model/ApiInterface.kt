package com.ictu.news.dev.duc.model

import com.ictu.news.dev.duc.collection.ListNewFeedCollection
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("newsfeed")
    fun loadNewFeed(@Query("page") index: String): Call<ListNewFeedCollection>

    @GET("newsfeed/vnreview")
    fun loadVnreviewNewFeed(@Query("page") index: String): Call<ListNewFeedCollection>

    @GET("newsfeed/toidicodedao")
    fun loadToidicodedaoNewFeed(@Query("page") index: String): Call<ListNewFeedCollection>
}