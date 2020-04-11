package com.lielle.duoduoeyepetizer.modulehome

import android.annotation.SuppressLint
import android.content.Context
import com.lielle.duoduoeyepetizer.util.extension.applySchedules
import com.lielle.duoduoeyepetizer.model.bean.HomeBean
import com.lielle.duoduoeyepetizer.model.HomeModel
import com.lielle.duoduoeyepetizer.mvp.HomeContract
import io.reactivex.Observable

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/26
 */
class HomePresent(context: Context?, homeView: HomeContract.HomeView) : HomeContract.Present {

    var mContext: Context? = null
    var mView: HomeContract.HomeView? = null

    val mModel: HomeModel by lazy {
        HomeModel()
    }

    init {
        mContext = context
        mView = homeView
    }

    @SuppressLint("CheckResult")
    override fun requestData() {
        //执行异步获取网络数据
        val observable: Observable<HomeBean>? = mContext?.let { mModel.loadData(it, true, "0") }
        observable?.applySchedules()?.subscribe { mView?.setData(it) }

    }

    @SuppressLint("CheckResult")
    override fun requestMoreData(nextPage: String?) {
        val observable: Observable<HomeBean>? =
            mContext?.let { mModel.loadData(it, false, nextPage) }
        observable?.applySchedules()?.subscribe { mView?.setData(it) }

    }

    override fun start() {
        requestData()
    }
}