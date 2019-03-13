package com.ictu.news.dev.boong.model

import com.ictu.news.address.Address
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

    @GET("${Address.domain}/post/vnreview")
    fun loadPostContentVnreview(@Query("post_url") post_url: String): Call<PostContentCollection>

    @GET("${Address.domain}/post/toidicodedao")
    fun loadPostContentToidicodedao(@Query("post_url") post_url: String): Call<PostContentCollection>

    @GET("${Address.domain}/search")
    fun loadSearch(@Query("keyword") keyword: String): Call<NewFeedCollection>
}