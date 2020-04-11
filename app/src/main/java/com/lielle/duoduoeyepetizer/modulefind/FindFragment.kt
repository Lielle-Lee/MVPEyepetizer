package com.lielle.duoduoeyepetizer.modulefind

import androidx.recyclerview.widget.GridLayoutManager
import com.lielle.duoduoeyepetizer.R
import com.lielle.duoduoeyepetizer.model.bean.FindBean
import com.lielle.duoduoeyepetizer.mvp.FindContract
import com.lielle.duoduoeyepetizer.BaseFragment

import kotlinx.android.synthetic.main.fragment_find_layout.*


/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/25
 */
class FindFragment: BaseFragment() , FindContract.FindView{

    var mAdapter: FindAdapter? = null
    var mPresent: FindPresent? = null

    override fun initView() {
        //init RecyclerView
        discover_layout.layoutManager = GridLayoutManager(context,2)
        mAdapter = FindAdapter(context)
        discover_layout.adapter = mAdapter
        discover_layout.addItemDecoration(SpacesItemDecoration(context!!, 5))
        //load data
        mPresent = FindPresent(context, this)
        mPresent!!.start()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_find_layout
    }

    override fun setData(dataList: MutableList<FindBean>) {
        mAdapter?.updateData(dataList)
    }
}