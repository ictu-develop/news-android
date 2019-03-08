package com.ictu.news.dev.boong.model

import com.ictu.news.dev.boong.collection.NewFeedCollection
import com.ictu.news.dev.boong.collection.PostContentCollection
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("newsfeed")
    fun loadNewFeed(@Query("page") index: String): Call<NewFeedCollection>

    @GET("newsfeed/vnreview")
    fun loadVnreviewNewFeed(@Query("page") index: String): Call<NewFeedCollection>

    @GET("newsfeed/toidicodedao")
    fun loadToidicodedaoNewFeed(@Query("page") index: String): Call<NewFeedCollection>

    @GET("http://80.211.52.162:2500/post/vnreview")
    fun loadPostContentVnreview(@Query("post_url") post_url: String): Call<PostContentCollection>

    @GET("http://80.211.52.162:2500/post/toidicodedao")
    fun loadPostContentToidicodedao(@Query("post_url") post_url: String): Call<PostContentCollection>

    @GET("http://80.211.52.162:2500/search")
    fun loadSearch(@Query("keyword") keyword: String): Call<NewFeedCollection>
}