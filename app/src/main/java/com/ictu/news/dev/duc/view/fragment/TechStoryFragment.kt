package com.ictu.news.dev.duc.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ictu.news.R
import com.ictu.news.dev.duc.collection.ListNewFeedCollection
import com.ictu.news.dev.duc.collection.NewFeedCollection
import com.ictu.news.dev.duc.model.RequestToidicodedaoNewFeedModel
import com.ictu.news.dev.duc.presenter.RequestNewFeedPresenter
import com.ictu.news.dev.duc.view.adapter.RecyclerViewAdapter
import com.ictu.news.dev.duc.view.inteface.OnLoadMore
import com.ictu.news.dev.duc.view.inteface.OnRecyclerViewItemClickListener
import com.ictu.news.dev.duc.view.inteface.OnRequestNewFeedResult
import com.ictu.news.dev.kien.view.PostActivity
import kotlinx.android.synthetic.main.fragment_tech_story.*

class TechStoryFragment : Fragment() {

    // Index of page
    private var index = 1
    // Status request of newfeed
    private var requested = false
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    // collection sẽ không được khởi tạo trừ khi biến được sử dụng lần đầu tiên
    private val collection by lazy { ArrayList<NewFeedCollection>() }

    // Event click item in recyclerView
    private val recyclerViewItemClickListener by lazy {
        object : OnRecyclerViewItemClickListener {
            override fun onItemClick(view: View, postion: Int) {
                if (collection[postion].date == "null" && collection[postion].date == collection[postion].description &&
                    collection[postion].date == collection[postion].full_post && collection[postion].date == collection[postion].image &&
                    collection[postion].date == collection[postion].full_post) {
                } else {
                    val fullpost = collection[postion].full_post
                    val intent = Intent(requireContext(), PostActivity::class.java)
                    intent.putExtra("fullpost", fullpost)
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
                collection.add(NewFeedCollection("null", "null", "null", "null", "null", "null"))
                recyclerViewAdapter.notifyDataSetChanged()

                // Push index
                index++

                // Request next page
                RequestToidicodedaoNewFeedModel(requestToidicodedaoNewFeedResult).request(index)
            }
        }
    }

    // Event after requested
    private val requestToidicodedaoNewFeedResult by lazy {
        object : OnRequestNewFeedResult {
            override fun onDone(newFeedCollection: ListNewFeedCollection) {
                Toast.makeText(requireContext(), "Load Done", Toast.LENGTH_SHORT).show()

                if (index > 1)
                    collection.removeAt(collection.size - 1)

                if (newFeedCollection.code == "200")
                    for (item in newFeedCollection.post)
                        collection.add(item)

                //if (newFeedCollection.code == "204" && newFeedCollection.post.isEmpty())
                //Toast.makeText(requireContext(), "Nothing to show", Toast.LENGTH_SHORT).show()

                recyclerViewAdapter.notifyDataSetChanged()
                requested = true
            }

            override fun onFail() {
                if (index > 1)
                    collection.removeAt(collection.size - 1)

                recyclerViewAdapter.notifyDataSetChanged()
                requested = true

                Toast.makeText(requireContext(), "Load Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

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

                // Current recycler size
                val itemSizeNow = linearLayoutManager.itemCount
                // last position item visible
                val lastPositionItemVisible = linearLayoutManager.findLastVisibleItemPosition()

                if (itemSizeNow - 1 == lastPositionItemVisible) {
                    // After pre page loaded then load next page
                    if (requested) {
                        requested = false

                        // On load more
                        onLoadMore.OnMore()
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
        init()
        configRecyclerView()
        RequestToidicodedaoNewFeedModel(requestToidicodedaoNewFeedResult).request(index)
    }
}
