package com.lielle.duoduoeyepetizer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/26
 */
abstract class BaseFragment : Fragment() {

    var mRootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutResource(), container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * 初始化相关View
     */
    abstract fun initView()

    /**
     * 获取layout资源
     */
    abstract fun getLayoutResource(): Int
}