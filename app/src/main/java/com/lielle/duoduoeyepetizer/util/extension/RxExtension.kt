package com.lielle.duoduoeyepetizer.util.extension

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/27
 */

fun <T> Observable<T>.applySchedules(): Observable<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.io())
}
