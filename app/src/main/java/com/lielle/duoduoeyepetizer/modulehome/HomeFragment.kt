package com.lielle.duoduoeyepetizer.modulehome

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lielle.duoduoeyepetizer.R
import com.lielle.duoduoeyepetizer.model.bean.HomeBean
import com.lielle.duoduoeyepetizer.model.bean.ItemBean
import com.lielle.duoduoeyepetizer.mvp.HomeContract
import com.lielle.duoduoeyepetizer.BaseFragment

import kotlinx.android.synthetic.main.fragment_home_layout.*
import java.util.regex.Pattern

/**
 *
 * @des 首页展示
 * @author lielleli
 * @time 2020/3/25
 */
class HomeFragment : BaseFragment(), HomeContract.HomeView, SwipeRefreshLayout.OnRefreshListener {

    var mIsRefresh: Boolean = false
    var mPresent: HomePresent? = null
    var mAdapter: HomeAdapter? = null
    var datalist: ArrayList<ItemBean> = ArrayList()
    var date: String? = null

    override fun initView() {
        //init recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = HomeAdapter(context)
        recyclerView.adapter = mAdapter
        recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager = recyclerView.layoutManager as LinearLayoutManager
                var lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == datalist.size - 1) {
                    //加载下一页数据
                    if (date != null) {
                        mPresent?.requestMoreData(date)
                    }
                }
            }
        })
        //init refresh layout
        refreshLayout.setOnRefreshListener(this)
        mPresent = HomePresent(context, this)
        mPresent?.start()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_home_layout
    }

    override fun setData(homeBean: HomeBean) {
        //正则过滤,获取下一页关键数据
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(homeBean?.nextPageUrl)
        date = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        Log.d("Lielle", date)
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (datalist.size > 0) {
                datalist.clear()
            }
        }
        //get list ItemBean from HomeBean
        homeBean.issueList!!
            .flatMap { it.itemList }
            .filter { it.type == "video" }
            .forEach { datalist.add(it) }
        mAdapter!!.updateData(datalist)
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresent?.start()
        }
    }
}