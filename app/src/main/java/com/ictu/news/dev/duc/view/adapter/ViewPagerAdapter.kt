package com.ictu.news.dev.duc.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, val layout: ArrayList<Fragment>, val title: ArrayList<String>): FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(p0: Int): Fragment {
        return layout[p0]
    }

    override fun getCount(): Int {
        return layout.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}