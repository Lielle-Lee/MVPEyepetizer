package com.lielle.duoduoeyepetizer.mvp

import com.lielle.duoduoeyepetizer.model.bean.HotBean

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/3
 */
interface SearchContract {

    interface Present: BasePresent {
        fun requestData(keyWords: String, startIndex: Int)
    }

    interface SearchView: BaseView<Present> {
        fun setData(bean: HotBean)
    }
}