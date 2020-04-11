package com.lielle.duoduoeyepetizer.model.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 *
 * @des Parcelable数据类 VideoBean
 * @author lielleli
 * @time 2020/4/3
 */
data class VideoBean(
    val feed: String?, val title: String?, val des: String?,
    val duration: Long?, val playUrl: String?, val category: String?,
    val blurred: String?, val collectNum: Int?, val shareNum: Int?, val replyNum: Int?,
    val time: Long?
) : Parcelable, Serializable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<VideoBean> = object : Parcelable.Creator<VideoBean> {
            override fun createFromParcel(source: Parcel?): VideoBean {
                return VideoBean(source!!)
            }

            override fun newArray(size: Int): Array<VideoBean?> {
                return arrayOfNulls<VideoBean>(size)
            }

        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(), parcel.readString(), parcel.readString(),
        parcel.readLong(), parcel.readString(), parcel.readString(),
        parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readLong()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(feed)
        dest.writeString(title)
        dest.writeString(des)
        dest.writeLong(duration!!)
        dest.writeString(playUrl)
        dest.writeString(category)
        dest.writeString(blurred)
        dest.writeValue(collectNum)
        dest.writeValue(shareNum)
        dest.writeValue(replyNum)
        dest.writeLong(time!!)
    }

    override fun describeContents(): kotlin.Int = 0
}