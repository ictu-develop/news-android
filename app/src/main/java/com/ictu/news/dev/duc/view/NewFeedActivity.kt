package com.ictu.news.dev.duc.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.ictu.news.R
import com.ictu.news.dev.duc.collection.ListNewFeedCollection
import com.ictu.news.dev.duc.collection.NewFeedCollection
import com.ictu.news.dev.duc.presenter.RequestNewFeedPresenter
import com.ictu.news.dev.duc.view.adapter.RecyclerViewAdapter
import com.ictu.news.dev.duc.view.inteface.RequestNewfeedResult
import kotlinx.android.synthetic.main.activity_new_feed.*


class NewFeedActivity : AppCompatActivity() {

    private val requestNewsFeedResult by lazy {
        object : RequestNewfeedResult {
            override fun onDone(newFeedCollection: ListNewFeedCollection) {
                Toast.makeText(this@NewFeedActivity, "Load Done", Toast.LENGTH_SHORT).show()
                if (newFeedCollection.code == "200")
                    for (item in newFeedCollection.post)
                        collection.add(item)

                recyclerViewAdapter.notifyDataSetChanged()
            }

            override fun onFail() {
                Toast.makeText(this@NewFeedActivity, "Load Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    //collection sẽ không được khởi tạo trừ khi biến được sử dụng lần đầu tiên
    private val collection by lazy { ArrayList<NewFeedCollection>() }

    private fun init() {
        recyclerViewAdapter = RecyclerViewAdapter(this, collection)
    }

    private fun configRecyclerView() {
        // config layoutManager
        recycler_view.layoutManager = LinearLayoutManager(this)

        // config Adapter
        recycler_view.adapter = recyclerViewAdapter
    }

    private fun run() {
        init()
        configRecyclerView()
        RequestNewFeedPresenter(requestNewsFeedResult).request(1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_feed)
        run()
    }
}
