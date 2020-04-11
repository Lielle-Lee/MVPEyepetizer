package com.lielle.duoduoeyepetizer

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity

//不再findViewById
import kotlinx.android.synthetic.main.activity_splash.*

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/24
 */
class SplashActivity : AppCompatActivity() {

    companion object {
        val TAG : String = "SplashActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        initView()
        initAnimation()
    }

    private fun initAnimation() {
        val animation = AlphaAnimation(0.1f, 1.0f)
        animation.duration = 1000 //ms
        val scaleAnimation = ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 1000

        val animSet = AnimationSet(true)
        animSet.addAnimation(animation)
        animSet.addAnimation(scaleAnimation)
        animSet.duration = 1000

        iv_icon_splash.startAnimation(animSet)
        animSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                Log.d(TAG, "start main activity")
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

            override fun onAnimationStart(animation: Animation?) {
                Log.d(TAG, "animation start")
            }

            override fun onAnimationRepeat(animation: Animation?) {
                Log.d(TAG, "animation repeat")
            }
        })


    }

    private fun initView() {
        //更换字体
        val font : Typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tv_name_english.typeface = font
        tv_english_intro.typeface = font
    }

}