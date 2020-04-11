package com.lielle.duoduoeyepetizer.modulesearch.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lielle.duoduoeyepetizer.R
import com.lielle.duoduoeyepetizer.model.bean.HotBean
import com.lielle.duoduoeyepetizer.model.bean.ItemData
import com.lielle.duoduoeyepetizer.mvp.SearchContract
import kotlinx.android.synthetic.main.activity_search_result.*

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/3
 */
class ResultActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    SearchContract.SearchView {

    lateinit var keyWords: String
    lateinit var mAdapter: SearchResultAdapter
    lateinit var mPresent: SearchPresent
    var mIsRefresh: Boolean = false
    var mList: ArrayList<ItemData> = ArrayList()


    var start: Int = 10

    companion object {
        const val key = "KeyWords"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        keyWords = intent.getStringExtra(key)
        initToolBar()
        initResultRecyclerView()
        refresh_layout.setOnRefreshListener(this)
        loadData()
    }

    private fun loadData() {
        mPresent = SearchPresent(
            this,
            this
        )
        mPresent.requestData(keyWords, start)
    }

    private fun initResultRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        mAdapter =
            SearchResultAdapter(
                this
            )
        recycler_view.adapter = mAdapter
        recycler_view.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager = recyclerView.layoutManager as LinearLayoutManager
                var lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == mList.size - 1) {
                    //加载下一页数据
                    start = start.plus(10)
                    mPresent.requestData(keyWords, start)
                }
            }
        })
    }

    private fun initToolBar() {
        setSupportActionBar(tool_bar)
        var bar = supportActionBar
        bar?.title = "'$keyWords' 相关"
        bar?.setDisplayHomeAsUpEnabled(true)
        tool_bar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onRefresh() {
        //刷新数据
        if (!mIsRefresh) {
            mIsRefresh = true
            start = 10
            mPresent.requestData(keyWords, start)
        }
    }

    override fun setData(bean: HotBean) {
        if (mIsRefresh) {
            mIsRefresh = false
            refresh_layout.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }
        }
        bean.list?.forEach {
            it.data?.let { it1 -> mList.add(it1) }
        }
        mAdapter.updateList(mList)
    }
}