package com.lielle.duoduoeyepetizer.model.network

import com.lielle.duoduoeyepetizer.model.bean.FindBean
import com.lielle.duoduoeyepetizer.model.bean.HomeBean
import com.lielle.duoduoeyepetizer.model.bean.HotBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/26
 */
interface ApiService {


    companion object {
        val BASE_URL = "http://baobab.kaiyanapp.com/api/"
    }


    //获取首页第一页内容
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData(): Observable<HomeBean>

    //获取首页第一页之后的内容，需要传递参数 date num
    @GET("v2/feed")
    fun getHomeMoreData(@Query("date") data: String, @Query("num") page: String): Observable<HomeBean>

    //获取发现频道信息
    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getFindData(): Observable<MutableList<FindBean>>

    //获取热门信息
    @GET("v3/ranklist")
    fun getHotData(
        @Query("num") num: Int, @Query("strategy") strategy: String,
        @Query("udid") udid: String, @Query("vc") vc: Int
    ): Observable<HotBean>

    @GET("v1/search")
    fun getSearchData(
        @Query("num") num: Int,
        @Query("query") query: String,
        @Query("start") start: Int
    ): Observable<HotBean>
}