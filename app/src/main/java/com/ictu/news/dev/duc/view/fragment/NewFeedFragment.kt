package com.ictu.news.dev.duc.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast

import com.ictu.news.R
import com.ictu.news.dev.duc.collection.ListNewFeedCollection
import com.ictu.news.dev.duc.collection.NewFeedCollection
import com.ictu.news.dev.duc.presenter.RequestNewFeedPresenter
import com.ictu.news.dev.duc.view.adapter.RecyclerViewAdapter
import com.ictu.news.dev.duc.view.inteface.OnRecyclerViewItemClickListener
import com.ictu.news.dev.duc.view.inteface.OnRequestNewFeedResult
import kotlinx.android.synthetic.main.fragment_new_feed.*


class NewFeedFragment : Fragment() {

    private var index = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_feed, container, false)
    }

    // Event click item in recyclerView
    private val recyclerViewItemClickListener by lazy {
        object : OnRecyclerViewItemClickListener {
            override fun onItemClick(view: View, postion: Int) {
            }

            override fun onLongItemClick(view: View, postion: Int) {
            }
        }
    }


    // Event after requested
    private val requestNewsFeedResult by lazy {
        object : OnRequestNewFeedResult {
            override fun onDone(newFeedCollection: ListNewFeedCollection) {
                Toast.makeText(requireContext(), "Load Done", Toast.LENGTH_SHORT).show()
                if (newFeedCollection.code == "200")
                    for (item in newFeedCollection.post)
                        collection.add(item)

                recyclerViewAdapter.notifyDataSetChanged()
            }

            override fun onFail() {
                Toast.makeText(requireContext(), "Load Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    // collection sẽ không được khởi tạo trừ khi biến được sử dụng lần đầu tiên
    private val collection by lazy { ArrayList<NewFeedCollection>() }

    private fun init() {
        recyclerViewAdapter = RecyclerViewAdapter(requireContext(), collection, recyclerViewItemClickListener)
    }

    private fun configRecyclerView() {
        // Config layoutManager
        recycler_view.layoutManager = LinearLayoutManager(requireContext())

        // Config Adapter
        recycler_view.adapter = recyclerViewAdapter

        // Event Scroll
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

                val itemSizeNow = linearLayoutManager.itemCount
                val lastPositionItemVisible = linearLayoutManager.findLastVisibleItemPosition()

                //Toast.makeText(requireContext(), "$lastPositionItemVisible / $itemSizeNow", Toast.LENGTH_SHORT).show()

                if (itemSizeNow - 1 == lastPositionItemVisible) {
                    Toast.makeText(requireContext(), "Loading more", Toast.LENGTH_SHORT).show()
                    index++
                    RequestNewFeedPresenter(requestNewsFeedResult).request(index)
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        configRecyclerView()
        RequestNewFeedPresenter(requestNewsFeedResult).request(index)
    }
}
