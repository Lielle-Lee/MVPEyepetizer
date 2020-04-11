package com.lielle.duoduoeyepetizer

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.lielle.duoduoeyepetizer.model.bean.VideoBean
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils

import kotlinx.android.synthetic.main.activity_detail_layout.*
import java.io.FileInputStream
import java.io.InputStream
import java.lang.Exception


/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/3
 */
class DetailActivity : AppCompatActivity() {

    var mData: VideoBean? = null
    var isPlay = false
    var mHandler: Handler = @SuppressLint("HandlerLeak")
     object : Handler() {
         override fun handleMessage(msg: Message) {
             super.handleMessage(msg)
             if (msg?.what == MSG_IMAGE_LOADED) {
                 gsy_player.thumbImageView = mImageView
             }
         }
     }
    lateinit var mImageView: ImageView
    lateinit var mOrientationUtils: OrientationUtils

    companion object {
        const val dataKey = "data"
        const val localKey = "localFile"
        const val MSG_IMAGE_LOADED = 111
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_layout)
        //get data
        mData = intent.getParcelableExtra(dataKey)
        Log.d("Lielle", mData.toString())
        initView()
        prepareVideo()
    }

    private fun prepareVideo() {
        var uri = intent.getStringExtra(localKey)
        if (uri != null) {
            gsy_player.setUp(uri, false, null, null)
        } else {
            gsy_player.setUp(mData?.playUrl, false, null, null)
        }
        mImageView = ImageView(this)
        mImageView.scaleType = ImageView.ScaleType.CENTER_CROP
        //异步加载Image对应内容，为player设置封面
        ImageViewAsyncTask(mHandler, this).execute(mData?.feed)

        gsy_player.titleTextView.visibility = View.GONE
        gsy_player.backButton.visibility = View.VISIBLE
        gsy_player.isRotateViewAuto = false
        gsy_player.isLockLand = false
        gsy_player.isShowFullAnimation = false
        gsy_player.isNeedLockFull = true

        mOrientationUtils = OrientationUtils(this, gsy_player)
        gsy_player.setIsTouchWiget(true)
        gsy_player.fullscreenButton.setOnClickListener {
            mOrientationUtils.resolveByClick()
            gsy_player.startWindowFullscreen(this@DetailActivity, true, true)
        }
        gsy_player.setVideoAllCallBack(object : VideoAllCallBack {
            override fun onClickResumeFullscreen(url: String?, vararg objects: Any?) {
            }

            override fun onEnterFullscreen(url: String?, vararg objects: Any?) {
            }

            override fun onClickResume(url: String?, vararg objects: Any?) {
            }

            override fun onClickSeekbarFullscreen(url: String?, vararg objects: Any?) {
            }

            override fun onStartPrepared(url: String?, vararg objects: Any?) {
            }

            override fun onClickStartIcon(url: String?, vararg objects: Any?) {
            }

            override fun onTouchScreenSeekLight(url: String?, vararg objects: Any?) {
            }

            override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                mOrientationUtils.let { mOrientationUtils.backToProtVideo() }
            }

            override fun onClickStartThumb(url: String?, vararg objects: Any?) {
            }

            override fun onEnterSmallWidget(url: String?, vararg objects: Any?) {
            }

            override fun onClickStartError(url: String?, vararg objects: Any?) {
            }

            override fun onClickBlankFullscreen(url: String?, vararg objects: Any?) {
            }

            override fun onPrepared(url: String?, vararg objects: Any?) {
                //准备好播放前相关事宜
                mOrientationUtils.isEnable = true
                isPlay = true

            }

            override fun onAutoComplete(url: String?, vararg objects: Any?) {
               // do nothing yet
            }

            override fun onQuitSmallWidget(url: String?, vararg objects: Any?) {
               // do nothing yet
            }

            override fun onTouchScreenSeekVolume(url: String?, vararg objects: Any?) {
                // do nothing yet
            }

            override fun onClickBlank(url: String?, vararg objects: Any?) {
                // do nothing yet
            }

            override fun onClickStop(url: String?, vararg objects: Any?) {
                // do nothing yet
            }

            override fun onClickSeekbar(url: String?, vararg objects: Any?) {
                // do nothing yet
            }

            override fun onPlayError(url: String?, vararg objects: Any?) {
                // do nothing yet
            }

            override fun onClickStopFullscreen(url: String?, vararg objects: Any?) {
                // do nothing yet
            }

            override fun onTouchScreenSeekPosition(url: String?, vararg objects: Any?) {
               // do nothing yet
            }


        })
        gsy_player.setLockClickListener { view, lock -> mOrientationUtils.isEnable = !lock }
        gsy_player.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initView() {
        Glide.with(this).load(mData?.blurred).into(video_bg)
        video_title.text = mData?.title
        des.text = mData?.des
        var category = mData?.category
        var duration = mData?.duration

        var minute = duration?.div(60)
        var second = duration?.minus(minute?.times(60) as Long)
        time.text = "$category / $minute ' $second ''"


        collect.text = "收藏：" + mData?.collectNum.toString()
        share.text = "分享:" + mData?.shareNum.toString()
        comment.text = "评论:" + mData?.replyNum.toString()
        download.setOnClickListener {
            //TODO 执行下载逻辑
        }
    }


    inner class ImageViewAsyncTask(var handler: Handler,  var activity: DetailActivity) : AsyncTask<String, Void, String>() {

        private var mPath: String? = null
        private var mInputStream: InputStream? = null

        override fun doInBackground(vararg feed: String?): String {
            val future = Glide.with(activity).load(feed[0]).downloadOnly(100, 100)
            try {
                val cacheFile = future.get()
                mPath = cacheFile.absolutePath

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return mPath!!
        }

        override fun onPostExecute(path: String?) {
            super.onPostExecute(path)
            try {
                mInputStream = FileInputStream(path)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            val bitmap = BitmapFactory.decodeStream(mInputStream)
            mImageView.setImageBitmap(bitmap)
            var message = handler.obtainMessage()
            message.what = MSG_IMAGE_LOADED
            handler.sendMessage(message)
        }
    }
}