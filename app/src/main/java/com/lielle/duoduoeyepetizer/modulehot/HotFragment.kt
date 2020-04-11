package com.lielle.duoduoeyepetizer.modulehot

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lielle.duoduoeyepetizer.R
import com.lielle.duoduoeyepetizer.BaseFragment
import kotlinx.android.synthetic.main.fragment_hot_layout.*

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/25
 */
class HotFragment : BaseFragment() {

    companion object {
        public const val KEY = "strategy"
    }
    val mTabs = listOf("周排行", "月排行", "总排行").toMutableList()

    lateinit var mFragments : ArrayList<Fragment>
    val STRATEGY = arrayOf("weekly", "monthly", "historical")

    override fun initView() {
        //init view pager
        initFragments()
        view_pager.adapter = HotAdapter(fragmentManager, mFragments, mTabs)
        //tablayout bind viewpager
        tab_layout.setupWithViewPager(view_pager)
    }

    private fun initFragments() {
        mFragments = ArrayList()
        var weekFragment = RankFragment()
        var weekBundle = Bundle()
        weekBundle.putString(KEY, STRATEGY[0])
        weekFragment.arguments = weekBundle
        mFragments.add(weekFragment as Fragment)

        var monthFragment = RankFragment()
        var monthBundle = Bundle()
        monthBundle.putString(KEY, STRATEGY[1])
        monthFragment.arguments = monthBundle
        mFragments.add(monthFragment as Fragment)

        var allFragment = RankFragment()
        var allBundle = Bundle()
        allBundle.putString(KEY, STRATEGY[2])
        allFragment.arguments = allBundle
        mFragments.add(allFragment as Fragment)
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_hot_layout
    }
}