package com.lielle.duoduoeyepetizer.model

import android.content.Context
import com.lielle.duoduoeyepetizer.model.bean.FindBean
import com.lielle.duoduoeyepetizer.model.network.ApiService
import com.lielle.duoduoeyepetizer.model.network.RetrofitClient
import io.reactivex.Observable

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/30
 */
class FindModel {

    fun loadData(context: Context) : Observable<MutableList<FindBean>>? {
        val client : RetrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService =  client.create(ApiService::class.java)
        return apiService?.getFindData()
    }
}