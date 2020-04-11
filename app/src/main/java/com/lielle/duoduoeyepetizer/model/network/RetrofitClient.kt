package com.lielle.duoduoeyepetizer.model.network

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

/**
 *
 * @des 单例调起Retrofit
 * @author lielleli
 * @time 2020/3/26
 */
class RetrofitClient private constructor(context: Context, baseUrl: String) {

    var mHttpCacheDirectory: File? = null
    var mCache: Cache? = null
    var mOkHttpClient: OkHttpClient
    var mRetrofit: Retrofit

    var mContext: Context = context
    var mBaseUrl: String = baseUrl

    val DEFAULT_TIMEOUT: Long = 20
    val CACHE_DIR_NAME = "app_cache"

    init {
        if (mHttpCacheDirectory == null) {
            mHttpCacheDirectory = File(mContext.cacheDir, CACHE_DIR_NAME)
        }
        if (mCache == null) {
            mCache = Cache(mHttpCacheDirectory, 10 * 1024 * 1024)
        }
        //创建okhttp连接
        mOkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .cache(mCache)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()
        //创建retrofit，设置相关参数
        mRetrofit = Retrofit.Builder()
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(mBaseUrl)
            .build()
    }


    //单例实现
    companion object {

        var instance: RetrofitClient? = null

        fun getInstance(context: Context, baseUrl: String): RetrofitClient {
            if (instance == null) {
                synchronized(RetrofitClient::class) {
                    if (instance == null) {
                        instance = RetrofitClient(context, baseUrl)
                    }
                }
            }
            return instance!!
        }
    }

    /**
     * 创建Http请求
     */
    public fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw  RuntimeException("Api Service is null")
        }
        return mRetrofit?.create(service)
    }

}