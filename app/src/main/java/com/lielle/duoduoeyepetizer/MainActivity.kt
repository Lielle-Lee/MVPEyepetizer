package com.lielle.duoduoeyepetizer

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.lielle.duoduoeyepetizer.modulefind.FindFragment
import com.lielle.duoduoeyepetizer.modulehome.HomeFragment
import com.lielle.duoduoeyepetizer.modulehot.HotFragment
import com.lielle.duoduoeyepetizer.modulemyself.SelfFragment
import com.lielle.duoduoeyepetizer.modulesearch.SEARCH_TAG
import com.lielle.duoduoeyepetizer.modulesearch.SearchFragment

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    lateinit var mHomeFragment: HomeFragment
    lateinit var mFindFragment: FindFragment
    lateinit var mHotFragment: HotFragment
    lateinit var mSelfFragment: SelfFragment

    lateinit var mSearchFragment: SearchFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTopArea()
        initContentArea(savedInstanceState)
        initBottomArea()
    }

    private fun initBottomArea() {
        radio_home.isChecked = true
        bottom_radio_group.setOnCheckedChangeListener { group, checkedId ->
            Log.d(TAG, " click radio button")
            when (checkedId) {
                R.id.radio_home -> {
                    supportFragmentManager.beginTransaction().show(mHomeFragment)
                        .hide(mFindFragment)
                        .hide(mHotFragment)
                        .hide(mSelfFragment)
                        .commit()
                    top_title.visibility = View.VISIBLE
                    top_title.text = getToday()
                    search_icon.setImageResource(R.mipmap.icon_search)
                }
                R.id.radio_find -> {
                    supportFragmentManager.beginTransaction().show(mFindFragment)
                        .hide(mHomeFragment)
                        .hide(mHotFragment)
                        .hide(mSelfFragment)
                        .commit()
                    top_title.visibility = View.VISIBLE
                    top_title.text = "Discover"
                    search_icon.setImageResource(R.mipmap.icon_search)
                }

                R.id.radio_hot -> {
                    supportFragmentManager.beginTransaction().show(mHotFragment)
                        .hide(mHomeFragment)
                        .hide(mFindFragment)
                        .hide(mSelfFragment)
                        .commit()
                    top_title.visibility = View.VISIBLE
                    top_title.text = "Ranking"
                    search_icon.setImageResource(R.mipmap.icon_search)
                }

                R.id.radio_self -> {
                    supportFragmentManager.beginTransaction().show(mSelfFragment)
                        .hide(mHomeFragment)
                        .hide(mFindFragment)
                        .hide(mHotFragment)
                        .commit()
                    top_title.visibility = View.INVISIBLE
                    search_icon.setImageResource(R.mipmap.icon_setting)
                }
            }
        }
    }

    private fun initContentArea(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val fragmentStack: List<Fragment> = supportFragmentManager.fragments
            for (item in fragmentStack) {
                when (item) {
                    is HomeFragment -> mHomeFragment = item
                    is FindFragment -> mFindFragment = item
                    is HotFragment -> mHotFragment = item
                    is SelfFragment -> mSelfFragment = item
                }
            }
        } else {
            mHomeFragment = HomeFragment()
            mFindFragment = FindFragment()
            mHotFragment = HotFragment()
            mSelfFragment =
                SelfFragment()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.content_area, mHomeFragment)
            fragmentTrans.add(R.id.content_area, mFindFragment)
            fragmentTrans.add(R.id.content_area, mHotFragment)
            fragmentTrans.add(R.id.content_area, mSelfFragment)
            fragmentTrans.commit()
        }

        supportFragmentManager.beginTransaction()
            .show(mHomeFragment)
            .hide(mFindFragment)
            .hide(mHotFragment)
            .hide(mSelfFragment)
            .commit()
    }

    private fun initTopArea() {
        top_title.text = getToday()
        top_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        search_icon.setOnClickListener {
            Log.d(TAG, "click search icon, start search dialog fragment")
            if (((bottom_radio_group[bottom_radio_group.childCount -1] as RadioButton)).isChecked) {
                //TODO 打开设置

            } else {
                mSearchFragment =
                    SearchFragment()
                mSearchFragment.show(supportFragmentManager,
                    SEARCH_TAG
                )
            }
        }
    }

    private fun getToday(): CharSequence? {
        //创建数组
        val weekList =
            arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        //calendar 获取星期数
        val weekIndex = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1
        return weekList[weekIndex]
    }
}
