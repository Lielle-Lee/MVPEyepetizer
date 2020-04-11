package com.lielle.duoduoeyepetizer.modulehot

import android.content.Context
import com.lielle.duoduoeyepetizer.util.extension.applySchedules
import com.lielle.duoduoeyepetizer.model.bean.HotBean
import com.lielle.duoduoeyepetizer.model.HotModel
import com.lielle.duoduoeyepetizer.mvp.HotContract
import io.reactivex.Observable

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/1
 */
class HotPresent(context: Context?, view: HotContract.HotView): HotContract.Present {

    var mContext : Context? = context
    var mView: HotContract.HotView = view
    val mModel: HotModel by lazy { HotModel() }

    override fun requestData(strategy: String) {
        val observer: Observable<HotBean>? = mContext?.let {
            mModel.loadData(it, strategy)
        }
        observer?.applySchedules()?.subscribe { mView.setData(it) }
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
