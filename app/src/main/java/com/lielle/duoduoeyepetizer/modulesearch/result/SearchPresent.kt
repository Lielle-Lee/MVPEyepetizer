package com.lielle.duoduoeyepetizer.modulesearch.result

import android.annotation.SuppressLint
import android.content.Context
import com.lielle.duoduoeyepetizer.util.extension.applySchedules
import com.lielle.duoduoeyepetizer.model.ResultModel
import com.lielle.duoduoeyepetizer.mvp.SearchContract

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/3
 */
class SearchPresent(val context: Context, val view: SearchContract.SearchView) :
    SearchContract.Present {

    val mModel: ResultModel by lazy { ResultModel() }

    @SuppressLint("CheckResult")
    override fun requestData(keyWords: String, startIndex: Int) {
        var result = mModel.loadData(context, keyWords, startIndex)
        result?.applySchedules()?.subscribe {
            view.setData(it)
        }
    }


    override fun start() {

    }
}