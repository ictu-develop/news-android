package com.ictu.news.dev.duc.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.ictu.news.R
import com.ictu.news.dev.duc.collection.ItemNewFeedCollection
import com.ictu.news.dev.duc.collection.NewFeedCollection
import com.ictu.news.dev.duc.presenter.RequestSearchPresenter
import com.ictu.news.dev.duc.view.NewFeedActivity
import com.ictu.news.dev.duc.view.PostActivity
import com.ictu.news.dev.duc.view.adapter.RecyclerViewAdapter
import com.ictu.news.dev.duc.view.inteface.OnRecyclerViewItemClickListener
import com.ictu.news.dev.duc.view.inteface.OnRequestSearchResult
import kotlinx.android.synthetic.main.activity_new_feed.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var requestSearchPresenter: RequestSearchPresenter
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var searchResult = ArrayList<ItemNewFeedCollection?>()
    private var keyWord: String? = ""

    private val onRequestSearchResult = object : OnRequestSearchResult {
        override fun onEmpty(result: NewFeedCollection) {
            searchResult.clear()
            searchResult.addAll(result.post)

            recyclerViewAdapter.notifyDataSetChanged()

            progress_bar.visibility = GONE
            recycler_view.visibility = VISIBLE
            (requireActivity() as NewFeedActivity).apply {
                keyWord?.let {
                    (requireActivity() as NewFeedActivity).apply {
                        this.search_title.text = "Không có kết quả cho '${it}'"
                    }
                }
            }
        }

        override fun onDone(result: NewFeedCollection) {
            searchResult.clear()
            searchResult.addAll(result.post)

            recyclerViewAdapter.notifyDataSetChanged()

            progress_bar.visibility = GONE
            recycler_view.visibility = VISIBLE

            keyWord?.let {
                (requireActivity() as NewFeedActivity).apply {
                    this.search_title.text = "Kết quả cho '${it}'"
                }
            }
        }

        override fun onFail(t: String) {
            recyclerViewAdapter.notifyDataSetChanged()

            progress_bar.visibility = GONE
            recycler_view.visibility = VISIBLE

            (requireActivity() as NewFeedActivity).apply {
                this.search_title.text = "Có lỗi"
            }
        }
    }

    private var recyclerViewItemClickListener = object : OnRecyclerViewItemClickListener {
        override fun onItemClick(view: View, postion: Int) {
            (requireActivity() as NewFeedActivity).apply {
                val post = searchResult
                if (post[postion]!!.date == "null" && post[postion]!!.date == post[postion]!!.description &&
                    post[postion]!!.date == post[postion]!!.full_post && post[postion]!!.date == post[postion]!!.image &&
                    post[postion]!!.date == post[postion]!!.full_post
                ) {
                } else {
                    val link = post[postion]!!.link
                    val source = post[postion]!!.source
                    val intent = Intent(requireContext(), PostActivity::class.java)
                    intent.putExtra("link", link)
                    intent.putExtra("source", source)
                    startActivity(intent)
                }
            }
        }

        override fun onLongItemClick(view: View, postion: Int) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private fun configRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter(requireContext(), searchResult, recyclerViewItemClickListener)

        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.adapter = recyclerViewAdapter
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()

        keyWord = arguments?.getString("key_word")

        keyWord?.let {
            (requireActivity() as NewFeedActivity).apply {
                this.search_title.text = "Kết quả cho '${it}'"
            }
            requestSearchPresenter = RequestSearchPresenter(onRequestSearchResult)
            requestSearchPresenter.search(it)
        }
    }
}
