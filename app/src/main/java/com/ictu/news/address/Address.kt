package com.ictu.news.address

class Address {

    companion object {

        const val domain = "http://80.211.52.162:2500/"

        fun newFeed(index: Int): String {
            return "http://80.211.52.162:2500/newsfeed?page=$index"
        }

        fun newFeedVnreview(index: Int): String {
            return "http://80.211.52.162:2500/newsfeed/vnreview?page=$index"
        }

        fun newFeedToidicodedao(index: Int): String {
            return "http://80.211.52.162:2500/newsfeed/toidicodedao?page=$index"
        }

        fun post(webName: String, postUrl: String): String {
            return "http://80.211.52.162:2500/post/$webName?post_url=$postUrl"
        }
    }

}