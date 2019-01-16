package com.ictu.news.dev.duc.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.ictu.news.R
import com.ictu.news.dev.duc.view.fragment.NewFeedFragment
import com.ictu.news.dev.duc.view.fragment.TechNewsFragment
import com.ictu.news.dev.duc.view.fragment.TechStoryFragment
import kotlinx.android.synthetic.main.activity_new_feed.*


class NewFeedActivity : AppCompatActivity() {

    // Init Fragment
    private val newFeedFragment = NewFeedFragment()
    private val techNewsFragment = TechNewsFragment()
    private val techStoryFragment = TechStoryFragment()

    private fun hiddenFragment() {
        if (supportFragmentManager.findFragmentByTag("NewFeedFrag") != null)
            supportFragmentManager.beginTransaction().hide(newFeedFragment).commit()

        if (supportFragmentManager.findFragmentByTag("TechNewsFrag") != null)
            supportFragmentManager.beginTransaction().hide(techNewsFragment).commit()

        if (supportFragmentManager.findFragmentByTag("TechStoryFrag") != null)
            supportFragmentManager.beginTransaction().hide(techStoryFragment).commit()
    }

    // Config tabLayout
    private fun configTabLayout() {
        // Add new tabLayout
        tab_layout.addTab(tab_layout.newTab().setText("NewFeed"))
        tab_layout.addTab(tab_layout.newTab().setText("Tech News"))
        tab_layout.addTab(tab_layout.newTab().setText("Tech Story"))

        // Event tabLayout
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0?.text) {
                    "NewFeed" -> {
                        hiddenFragment()

                        if (supportFragmentManager.findFragmentByTag("NewFeedFrag") == null)
                            supportFragmentManager.beginTransaction().add(R.id.new_feed_content_layout, newFeedFragment, "NewFeedFrag")
                                .commit()
                        else
                            supportFragmentManager.beginTransaction().show(newFeedFragment)
                                .commit()
                    }

                    "Tech News" -> {
                        hiddenFragment()

                        if (supportFragmentManager.findFragmentByTag("TechNewsFrag") == null)
                            supportFragmentManager.beginTransaction().add(R.id.new_feed_content_layout, techNewsFragment, "TechNewsFrag")
                                .commit()
                        else
                            supportFragmentManager.beginTransaction().show(techNewsFragment)
                                .commit()
                    }

                    "Tech Story" -> {
                        hiddenFragment()

                        if (supportFragmentManager.findFragmentByTag("TechStoryFrag") == null)
                            supportFragmentManager.beginTransaction().add(R.id.new_feed_content_layout, techStoryFragment, "TechStoryFrag")
                                .commit()
                        else
                            supportFragmentManager.beginTransaction().show(techStoryFragment)
                                .commit()
                    }

                }
            }

        })
    }

    // Show when first open app
    private fun showNewFeed() {
        val posSelectDefault = tab_layout.selectedTabPosition
        if (posSelectDefault == 0)
            if (supportFragmentManager.findFragmentByTag("NewFeedFrag") == null)
                supportFragmentManager.beginTransaction().add(R.id.new_feed_content_layout, newFeedFragment, "NewFeedFrag")
                    .commit()

    }

    private fun run() {
        configTabLayout()
        showNewFeed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_feed)
        run()
    }
}
