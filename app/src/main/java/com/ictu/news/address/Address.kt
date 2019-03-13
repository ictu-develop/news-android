package com.ictu.news.address

class Address {

    companion object {

        const val domain = "https://news-api-vn.herokuapp.com"

        fun newFeed(index: Int): String {
            return "https://news-api-vn.herokuapp.com/newsfeed?page=$index"
        }

        fun newFeedVnreview(index: Int): String {
            return "https://news-api-vn.herokuapp.com/newsfeed/vnreview?page=$index"
        }

        fun newFeedToidicodedao(index: Int): String {
            return "https://news-api-vn.herokuapp.com/newsfeed/toidicodedao?page=$index"
        }

        fun post(webName: String, postUrl: String): String {
            return "https://news-api-vn.herokuapp.com/post/$webName?post_url=$postUrl"
        }
    }

}