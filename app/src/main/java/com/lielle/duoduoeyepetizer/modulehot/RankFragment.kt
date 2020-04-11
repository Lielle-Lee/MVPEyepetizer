package com.lielle.duoduoeyepetizer.modulehot

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.lielle.duoduoeyepetizer.R
import com.lielle.duoduoeyepetizer.model.bean.HotBean
import com.lielle.duoduoeyepetizer.model.bean.ItemData
import com.lielle.duoduoeyepetizer.mvp.HotContract
import com.lielle.duoduoeyepetizer.BaseFragment
import kotlinx.android.synthetic.main.fragment_rank_layout.*

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/30
 */
class RankFragment : BaseFragment(), HotContract.HotView {

    var mAdapter: RankAdapter? = null
    lateinit var mStategy: String
    lateinit var mPresent: HotPresent

    override fun initView() {
        //init recycler view
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = RankAdapter(context)
        recyclerView.adapter = mAdapter
        // get data by different arguments
        if (arguments != null) {
            mStategy = arguments?.getString(HotFragment.KEY)!!
            mPresent = HotPresent(context!!, this)
            mPresent.requestData(mStategy)
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_rank_layout
    }

    override fun setData(bean: HotBean) {
        //提取其中我需要的信息
        var list: ArrayList<ItemData> = ArrayList()
        bean.list?.forEach { it.data?.let { it1 -> list.add(it1) } }
        mAdapter?.updateList(list)
    }

}