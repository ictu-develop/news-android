package com.ictu.news.dev.boong.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ictu.news.R
import com.ictu.news.dev.boong.collection.ItemNewFeedCollection
import com.ictu.news.dev.boong.collection.NewFeedCollection
import com.ictu.news.dev.boong.presenter.RequestNewFeedPresenter
import com.ictu.news.dev.boong.presenter.RequestToidicodedaoNewFeedPresenter
import com.ictu.news.dev.boong.presenter.RequestVnreviewNewFeedPresenter
import com.ictu.news.dev.boong.view.PostActivity
import com.ictu.news.dev.boong.view.adapter.RecyclerViewAdapter
import com.ictu.news.dev.boong.view.interface_.OnLoadMore
import com.ictu.news.dev.boong.view.interface_.OnRecyclerViewItemClickListener
import com.ictu.news.dev.boong.view.interface_.OnRequestRssResult
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    // tuong duong voi static trong Java
    companion object {

        val NEWFEED = 1
        val TECHNEW = 2
        val TECHSTORY = 3
        val SEARCH = 4

        fun tab(type: Int): FeedFragment {
            val feedFragment = FeedFragment()
            val bundle = Bundle()

            when (type) {
                NEWFEED -> {
                    bundle.putInt("type", NEWFEED)
                    feedFragment.arguments = bundle
                }
                TECHNEW -> {
                    bundle.putInt("type", TECHNEW)
                    feedFragment.arguments = bundle
                }
                TECHSTORY -> {
                    bundle.putInt("type", TECHSTORY)
                    feedFragment.arguments = bundle
                }
                SEARCH -> {
                    bundle.putInt("type", SEARCH)
                    feedFragment.arguments = bundle
                }
            }

            return feedFragment
        }
    }

    // Index of page
    private var index = 1
    // Is load more
    private var isLoading = false
    // Is end of Newfeed
    private var isLast = false
    private var isType = -1
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private val collection = ArrayList<ItemNewFeedCollection?>()


    // Event click item in recyclerView
    private val recyclerViewItemClickListener = object : OnRecyclerViewItemClickListener {
        override fun onItemClick(view: View, postion: Int) {
            if (collection[postion]!!.date == "null" && collection[postion]!!.date == collection[postion]!!.description &&
                collection[postion]!!.date == collection[postion]!!.full_post && collection[postion]!!.date == collection[postion]!!.image &&
                collection[postion]!!.date == collection[postion]!!.full_post
            ) {
            } else {
                val link = collection[postion]!!.link
                val source = collection[postion]!!.source
                val intent = Intent(requireContext(), PostActivity::class.java)
                intent.putExtra("link", link)
                intent.putExtra("source", source)
                startActivity(intent)
            }
        }

        override fun onLongItemClick(view: View, postion: Int) {
        }
    }

    // Event On Load More
    private val onLoadMore = object : OnLoadMore {
        override fun OnMore() {
            // Add load more layout
            collection.add(null)
            recyclerViewAdapter.notifyDataSetChanged()

            // push index
            index++

            // Request next page
            when (isType) {
                NEWFEED -> {
                    RequestNewFeedPresenter(requestResult).request(index)
                }
                TECHNEW -> {
                    RequestVnreviewNewFeedPresenter(requestResult).request(index)
                }
                TECHSTORY -> {
                    RequestToidicodedaoNewFeedPresenter(requestResult).request(index)
                }
            }
        }
    }

    // Event after isLoading
    private val requestResult = object : OnRequestRssResult {
        override fun onDone(newFeedCollection: NewFeedCollection) {
            if (index > 1)
                collection.removeAt(collection.size - 1)

            recyclerViewAdapter.notifyItemRemoved(collection.size - 1)

            if (newFeedCollection.code == 200)
                for (item in newFeedCollection.post)
                    collection.add(item)

            recyclerViewAdapter.notifyDataSetChanged()

            if (newFeedCollection.code == 204 && newFeedCollection.post.isEmpty())
                isLast = true

            isLoading = false
        }

        override fun onFail(t: String) {
            isLoading = false

            if (index > 1)
                collection.removeAt(collection.size - 1)

            recyclerViewAdapter.notifyItemRemoved(collection.size - 1)
        }
    }

    private fun configRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter(requireContext(), collection, recyclerViewItemClickListener)

        // Config layoutManager
        recycler_view.layoutManager = LinearLayoutManager(requireContext())

        // Config Adapter
        recycler_view.adapter = recyclerViewAdapter

        // Event Scroll
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

                // Is scroll down
                if (dy > 0) {
                    // Current recycler size
                    val itemSizeNow = linearLayoutManager.itemCount
                    // last position item visible
                    val lastPositionItemVisible = linearLayoutManager.findLastVisibleItemPosition()

                    if (itemSizeNow - 1 == lastPositionItemVisible) {

                        // After pre page loaded then load next page
                        if (!isLoading && !isLast) {
                            isLoading = true

                            // On load more
                            onLoadMore.OnMore()
                        }
                    }
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = arguments?.getInt("type")

        type?.let {
            isType = it
            configRecyclerView()

            when (isType) {
                NEWFEED -> {
                    RequestNewFeedPresenter(requestResult).request(index)
                }
                TECHNEW -> {
                    RequestVnreviewNewFeedPresenter(requestResult).request(index)
                }
                TECHSTORY -> {
                    RequestToidicodedaoNewFeedPresenter(requestResult).request(index)
                }
            }
        }
    }
}
