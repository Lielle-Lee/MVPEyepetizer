package com.lielle.duoduoeyepetizer.mvp

import com.lielle.duoduoeyepetizer.model.bean.FindBean

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/27
 */
interface FindContract {

    interface Present: BasePresent {
        fun requestData()

    }
    interface FindView : BaseView<Present> {
        fun setData(beanList: MutableList<FindBean>)
    }

}