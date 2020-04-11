package com.lielle.duoduoeyepetizer.modulehot

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/30
 */
class HotAdapter(fm: FragmentManager?, fragmentList: ArrayList<Fragment>, titleList: MutableList<String>) : FragmentPagerAdapter(fm!!){

    var mFragmentManager = fm
    var mFragmentList = fragmentList
    var mTitleList = titleList

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList[position]
    }
}