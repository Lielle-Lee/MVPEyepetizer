package com.lielle.duoduoeyepetizer.model.bean

import com.google.gson.annotations.SerializedName

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/30
 */

data class FindBean(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("alias") val alias: Any?,
    @SerializedName("description") val des: String?,
    @SerializedName("bgPicture") val bgPic: String?,
    @SerializedName("bgColor") val bgColor: String?,
    @SerializedName("headerImage") val headerImage: String?,
    @SerializedName("defaultAuthorId") val defaultAuthor: Int,
    @SerializedName("tagId") val tagId: Int
)