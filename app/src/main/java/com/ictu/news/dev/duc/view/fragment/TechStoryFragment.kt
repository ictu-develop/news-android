package com.ictu.news.dev.duc.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ictu.news.R
import com.ictu.news.dev.duc.collection.NewFeedCollection
import com.ictu.news.dev.duc.collection.ItemNewFeedCollection
import com.ictu.news.dev.duc.presenter.RequestToidicodedaoNewFeedPresenter
import com.ictu.news.dev.duc.view.adapter.RecyclerViewAdapter
import com.ictu.news.dev.duc.view.inteface.OnLoadMore
import com.ictu.news.dev.duc.view.inteface.OnRecyclerViewItemClickListener
import com.ictu.news.dev.duc.view.inteface.OnRequestRssResult
import com.ictu.news.dev.duc.view.PostActivity
import kotlinx.android.synthetic.main.fragment_tech_story.*

class TechStoryFragment : Fragment() {

    // Index of page
    private var index = 1
    // Status load more
    private var isLoading = false
    // Is end of Newfeed
    private var isLast = false
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    // collection sẽ không được khởi tạo trừ khi biến được sử dụng lần đầu tiên
    private val collection by lazy { ArrayList<ItemNewFeedCollection?>() }

    // Event click item in recyclerView
    private val recyclerViewItemClickListener by lazy {
        object : OnRecyclerViewItemClickListener {
            override fun onItemClick(view: View, postion: Int) {
                if (collection[postion]!!.date == "null" && collection[postion]!!.date == collection[postion]!!.description &&
                    collection[postion]!!.date == collection[postion]!!.full_post && collection[postion]!!.date == collection[postion]!!.image &&
                    collection[postion]!!.date == collection[postion]!!.full_post) {
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
    }

    // Event On Load More
    private val onLoadMore by lazy {
        object : OnLoadMore {
            override fun OnMore() {
                // Add load more layout
                collection.add(null)
                recyclerViewAdapter.notifyDataSetChanged()

                // Push index
                index++

                // Request next page
                RequestToidicodedaoNewFeedPresenter(requestResult).request(index)
            }
        }
    }

    // Event after isLoading
    private val requestResult by lazy {
        object : OnRequestRssResult {
            override fun onDone(newFeedCollection: NewFeedCollection) {
                if (index > 1)
                    collection.removeAt(collection.size - 1)

                recyclerViewAdapter.notifyItemRemoved(collection.size - 1)

                if (newFeedCollection.code == 200) {
                    collection.clear()
                    collection.addAll(newFeedCollection.post)
                }

                recyclerViewAdapter.notifyDataSetChanged()

                if (newFeedCollection.code == 204 && newFeedCollection.post.isEmpty())
                    isLast = true

                isLoading = false
            }

            override fun onFail(t: String) {
                if (index > 1)
                    collection.removeAt(collection.size - 1)

                recyclerViewAdapter.notifyItemRemoved(collection.size - 1)

                isLoading = false
            }
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
        return inflater.inflate(R.layout.fragment_tech_story, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        RequestToidicodedaoNewFeedPresenter(requestResult).request(index)
    }
}
