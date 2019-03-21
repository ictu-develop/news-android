package com.ictu.news.dev.boong.model

import com.ictu.news.address.Address
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCommon {

    companion object {
        // Init retrofit builder
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Address.domain)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Api Service
        val apiService = retrofit.create(Api::class.java)
    }
}