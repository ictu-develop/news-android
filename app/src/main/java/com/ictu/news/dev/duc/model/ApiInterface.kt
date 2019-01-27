package com.ictu.news.dev.duc.model

import com.ictu.news.dev.duc.collection.ListNewFeedCollection
import com.ictu.news.dev.duc.collection.PostContentCollection
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

    @GET("http://80.211.52.162:2500/post/vnreview")
    fun loadPostContentVnreview(@Query("post_url") post_url: String): Call<PostContentCollection>

    @GET("http://80.211.52.162:2500/post/toidicodedao")
    fun loadPostContentToidicodedao(@Query("post_url") post_url: String): Call<PostContentCollection>
}