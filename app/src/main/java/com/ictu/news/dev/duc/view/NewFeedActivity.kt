package com.ictu.news.dev.duc.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View.*
import com.ictu.news.R
import com.ictu.news.dev.duc.view.adapter.ViewPagerAdapter
import com.ictu.news.dev.duc.view.fragment.NewFeedFragment
import com.ictu.news.dev.duc.view.fragment.SearchFragment
import com.ictu.news.dev.duc.view.fragment.TechNewsFragment
import com.ictu.news.dev.duc.view.fragment.TechStoryFragment
import kotlinx.android.synthetic.main.activity_new_feed.*

class NewFeedActivity : AppCompatActivity() {

    // Init Fragment
    private val newFeedFragment = NewFeedFragment()
    private val techNewsFragment = TechNewsFragment()
    private val techStoryFragment = TechStoryFragment()
    private val listFragment = ArrayList<Fragment>()

    private val listTabTitle = ArrayList<String>()

    private fun configViewPager() {
        listFragment.add(newFeedFragment)
        listFragment.add(techNewsFragment)
        listFragment.add(techStoryFragment)

        listTabTitle.add("Bảng tin")
        listTabTitle.add("C.nghệ")
        listTabTitle.add("Chuyện Coding")
        listTabTitle.add("Âm nhạc")
        listTabTitle.add("Phim ảnh")

        new_feed_view_pager.adapter = ViewPagerAdapter(supportFragmentManager, listFragment, listTabTitle)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        menu?.let {
            val searchItem = it.findItem(R.id.action_search)
            val searchView = searchItem.actionView as SearchView
            searchView.maxWidth = Integer.MAX_VALUE
            searchView.queryHint = "Tìm kiếm"

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    // Clear text
                    searchView.isIconified = true
                    // Close
                    searchView.onActionViewCollapsed()

                    // Request to search
                    p0?.let {
                        new_feed_view_pager.visibility = GONE
                        tab_layout.visibility = INVISIBLE
                        search_title.visibility = VISIBLE

                        val bundle = Bundle()
                        bundle.putString("key_word", p0)

                        val searchFragment = SearchFragment()
                        searchFragment.arguments = bundle

                        if (supportFragmentManager.findFragmentByTag("searchFragment") == null)
                            supportFragmentManager.beginTransaction().add(R.id.new_feed_content_layout, searchFragment, "searchFragment")
                                .commit()
                        else
                            supportFragmentManager.beginTransaction().replace(R.id.new_feed_content_layout, searchFragment, "searchFragment")
                                .commit()
                    }

                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }

            })
        }

        return true
    }

    // Config tabLayout
    private fun configTabLayout() {
        tab_layout.setupWithViewPager(new_feed_view_pager)

        // Event tabLayout
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
            }

        })
    }


    private fun configDrawerLayout() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun run() {
        configDrawerLayout()
        configViewPager()
        configTabLayout()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_feed)
        setSupportActionBar(toolbar)
        title = "Trang chủ"
        run()
    }

    override fun onBackPressed() {

        var exit = true

        supportFragmentManager.findFragmentByTag("searchFragment")?.let {
            supportFragmentManager.beginTransaction().remove(it)
                .commit()
            exit = false
            new_feed_view_pager.visibility = VISIBLE
            tab_layout.visibility = VISIBLE
            search_title.visibility = GONE
        }

        if (exit)
            super.onBackPressed()

    }
}
