package com.lielle.duoduoeyepetizer.modulesearch

import android.animation.Animator
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView

/**
 *
 * @des 主管搜索界面展开和收起的动画
 * @author lielleli
 * @time 2020/4/2
 */
class CircularRevealAnim {

    companion object {
        const val DURATION: Long = 200
    }

    interface AnimListener {

        fun onHideAnimationEnd()

        fun onShowAnimationEnd()
    }

    var mListener: AnimListener? = null

    fun setAnimListener(listener: AnimListener) {
        this.mListener = listener
    }

    fun hide(triggerView: View, rootView: View) {
        actionOtherVisible(false, triggerView, rootView)
    }


    fun show(triggerView: View, rootView: View) {
        actionOtherVisible(true, triggerView, rootView)
    }

    private fun actionOtherVisible(isShow: Boolean, triggerView: View, rootView: View) {
        /**
         * 计算 triggerView 的中心位置
         */
        val tvLocation = IntArray(2)
        triggerView.getLocationInWindow(tvLocation)
        val tvX = tvLocation[0] + triggerView.width / 2
        val tvY = tvLocation[1] + triggerView.height / 2

        /**
         * 计算 animView 的中心位置
         */
        val avLocation = IntArray(2)
        rootView.getLocationInWindow(avLocation)
        val avX = avLocation[0] + rootView.width / 2
        val avY = avLocation[1] + rootView.height / 2

        val rippleW = if (tvX < avX) rootView.width - tvX else tvX - avLocation[0]
        val rippleH = if (tvY < avY) rootView.height - tvY else tvY - avLocation[1]

        val maxRadius = Math.sqrt((rippleW * rippleW + rippleH * rippleH).toDouble()).toFloat()

        val startRadius: Float
        val endRadius: Float

        if (isShow) {
            startRadius = 0f
            endRadius = maxRadius
        } else {
            startRadius = maxRadius
            endRadius = 0f
        }

        val anim =
            ViewAnimationUtils.createCircularReveal(rootView, tvX, tvY, startRadius, endRadius)
        rootView.visibility = View.VISIBLE
        anim.duration = DURATION
        anim.interpolator = DecelerateInterpolator()
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                // do nothing yet
            }

            override fun onAnimationEnd(animation: Animator?) {
                if (isShow) {
                    rootView.visibility = View.VISIBLE
                    if (mListener != null) mListener!!.onShowAnimationEnd()
                } else {
                    rootView.visibility = View.GONE
                    if (mListener != null) mListener!!.onHideAnimationEnd()
                }
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }
        })
        anim.start()
    }
}

