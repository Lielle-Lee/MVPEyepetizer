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
 * @time 2020/4/3
 */
class ResultModel {

    fun loadData(context: Context, keyWords: String, startIndex: Int): Observable<HotBean>? {
        val client = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val request = client.create(ApiService::class.java)
        return request?.getSearchData(10, keyWords, startIndex)
    }

}