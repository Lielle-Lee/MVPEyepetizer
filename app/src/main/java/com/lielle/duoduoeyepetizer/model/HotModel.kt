package com.lielle.duoduoeyepetizer.model

import android.content.Context
import com.lielle.duoduoeyepetizer.model.bean.HotBean
import com.lielle.duoduoeyepetizer.model.network.ApiService
import com.lielle.duoduoeyepetizer.model.network.RetrofitClient
import io.reactivex.Observable

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/1
 */
class HotModel {
    fun loadData(mContext: Context, strategy: String): Observable<HotBean> {
        val client = RetrofitClient.getInstance(mContext, ApiService.BASE_URL)
        val request = client.create(ApiService::class.java)
        return request!!.getHotData(10, strategy, "26868b32e808498db32fd51fb422d00175e179df", 83)
    }
}