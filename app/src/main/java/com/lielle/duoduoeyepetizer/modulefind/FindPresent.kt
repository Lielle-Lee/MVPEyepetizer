package com.lielle.duoduoeyepetizer.modulefind

import android.content.Context
import com.lielle.duoduoeyepetizer.util.extension.applySchedules
import com.lielle.duoduoeyepetizer.model.bean.FindBean
import com.lielle.duoduoeyepetizer.model.FindModel
import com.lielle.duoduoeyepetizer.mvp.FindContract
import io.reactivex.Observable
/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/27
 */
class FindPresent(context: Context?, findView: FindContract.FindView) : FindContract.Present {

    var mContext: Context? = null
    var mView: FindContract.FindView? = null

    val mModel: FindModel by lazy {
        FindModel()
    }

    init {
        mContext = context
        mView = findView
    }

    override fun requestData() {
        val observable: Observable<MutableList<FindBean>>? = mContext?.let {
            mModel.loadData(mContext!!)
        }
        observable?.applySchedules()?.subscribe { mView?.setData(it) }
    }

    override fun start() {
        requestData()
    }
}