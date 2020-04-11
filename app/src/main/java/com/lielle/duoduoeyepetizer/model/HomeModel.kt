package com.lielle.duoduoeyepetizer.model

import android.content.Context
import com.lielle.duoduoeyepetizer.model.bean.HomeBean
import com.lielle.duoduoeyepetizer.model.network.ApiService
import com.lielle.duoduoeyepetizer.model.network.RetrofitClient
import io.reactivex.Observable

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/26
 */
class HomeModel {

    fun loadData(context: Context, isFirst: Boolean, data: String?): Observable<HomeBean>? {
        val client = RetrofitClient.getInstance(context, ApiService.BASE_URL)

        val homeApiService = client.create(ApiService::class.java)

        return when (isFirst) {
            true -> homeApiService?.getHomeData()

            false -> homeApiService?.getHomeMoreData(data.toString(), "2")
        }
    }
}