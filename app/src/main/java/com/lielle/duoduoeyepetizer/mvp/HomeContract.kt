package com.lielle.duoduoeyepetizer.mvp

import com.lielle.duoduoeyepetizer.model.bean.HomeBean

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/26
 */

interface HomeContract  {

    interface Present : BasePresent {
        fun requestData()
        fun requestMoreData(nextPage : String?)
    }

    interface HomeView : BaseView<Present> {
        fun setData(homeBean: HomeBean)
    }
}