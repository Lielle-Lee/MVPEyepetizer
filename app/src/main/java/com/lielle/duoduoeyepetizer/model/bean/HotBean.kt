package com.lielle.duoduoeyepetizer.model.bean

import com.google.gson.annotations.SerializedName

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/1
 */

data class HotBean(
    @SerializedName("count") val count: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("nextPageUrl") val nextPageUrl: String?,
    @SerializedName("adExist") val adExist: Boolean,
    @SerializedName("itemList") val list: List<ItemList>?
)

data class ItemList(
    @SerializedName("type") var type: String?,
    @SerializedName("data") var data: ItemData?,
    @SerializedName("tag") var tag: Any?
)

data class ItemData(
    var dataType: String?, var id: Int, var title: String?,
    var slogan: Any?, var description: String?, var provider: ProviderBean?,
    var category: String?, var author: Any?, var cover: CoverBean?,
    var playUrl: String?, var thumbPlayUrl: Any?, var duration: Long,
    var releaseTime: Long, var library: String?,
    var consumption: ConsumptionBean?, var campaign: Any?, var waterMarks: Any?,
    var adTrack: Any?, var type: String?, var titlePgc: Any?, var descriptionPgc: Any?,
    var remark: Any?, var idx: Int, var shareAdTrack: Any?, var favoriteAdTrack: Any?,
    var webAdTrack: Any?, var date: Long, var promotion: Any?, var label: Any?,
    var descriptionEditor: String?, var isCollected: Boolean, var isPlayed: Boolean,
    var lastViewTime: Any?, var playInfo: List<PlayInfoBean>?, var tags: List<TagsBean>?,
    var labelList: List<*>?, var subtitles: List<*>?
)

data class ProviderBean(var name: String?, var alias: String?, var icon: String?)

data class PlayInfoBean(
    var height: Int, var width: Int, var name: String?,
    var type: String?, var url: String?, var urlList: List<UrlListBean>?
)

data class UrlListBean(var name: String?,var url: String?,var size: Int)

data class TagsBean(var id: Int,var name: String?,var actionUrl: String?,var adTrack: Any?)

