package com.lielle.duoduoeyepetizer.mvp

import com.lielle.duoduoeyepetizer.model.bean.HotBean

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/1
 */
interface HotContract {

    interface Present : BasePresent {
        fun requestData(strategy: String)
    }

    interface HotView : BaseView<Present> {
        fun setData(bean: HotBean)
    }


}