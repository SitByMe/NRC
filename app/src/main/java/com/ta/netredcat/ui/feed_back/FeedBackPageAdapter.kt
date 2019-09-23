package com.ta.netredcat.ui.feed_back

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FeedBackPageAdapter(fm: FragmentManager, list: List<Fragment>) : FragmentPagerAdapter(fm) {

    private val fragmentList: List<Fragment> = list

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size
}