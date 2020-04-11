package com.lielle.duoduoeyepetizer.model.bean

import com.google.gson.annotations.SerializedName

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/26
 */
data class HomeBean(
    @SerializedName("nextPageUrl") var nextPageUrl: String?,
    @SerializedName("nextPublishTime") var nextPublishTime: Long,
    @SerializedName("newestIssueType") var IssueType: String,
    @SerializedName("dialog") var dialog: Any?,
    @SerializedName("issueList")
    var issueList: List<IssueListBean>?
)

data class IssueListBean(
    @SerializedName("releaseTime") var releaseTime: Long,
    @SerializedName("type") var type: String,
    @SerializedName("date") var date: String,
    @SerializedName("publishTime") var publishTime: Long,
    @SerializedName("itemList") var itemList: List<ItemBean>
)

data class ItemBean(@SerializedName("type") var type: String,
                    @SerializedName("data") var data: DataBean,
                    @SerializedName("tag") var tag: Any?,
                    @SerializedName("id") var id: Int)

data class DataBean(@SerializedName("dataType") var dataType: String,
                    @SerializedName("id") var id: Int,
                    @SerializedName("title") var title: String?,
                    @SerializedName("description") var des: String?,
                    @SerializedName("image") var image: String?,
                    @SerializedName("actionUrl") var actionUrl : String?,
                    @SerializedName("adTrack")var adTrack: Any?,
                    @SerializedName("shade")var isShade: Boolean,
                    @SerializedName("label")var label: Any?,
                    @SerializedName("labelList")var labelList: Any?,
                    @SerializedName("header")var header: Any?,
                    @SerializedName("category")var category: String?,
                    @SerializedName("duration")var duration: Long?,
                    @SerializedName("playUrl")var playUrl: String,
                    @SerializedName("cover")var cover: CoverBean?,
                    @SerializedName("author")var author: AuthorBean?,
                    @SerializedName("releaseTime")var releaseTime : Long?,
                    @SerializedName("consumption")var consumption: ConsumptionBean?)

data class CoverBean(@SerializedName("feed")var feed : String?,
                     @SerializedName("detail")var detail : String?,
                     @SerializedName("blurred")var blurred : String?,
                     @SerializedName("sharing")var sharing : String?,
                     @SerializedName("homepage")var homepage:String?)

data class AuthorBean(@SerializedName("icon")var icon: String)

data class ConsumptionBean(@SerializedName("collectionCount")var collectionCount: Int,
                           @SerializedName("shareCount")var shareCount: Int,
                           @SerializedName("replyCount")var replyCount: Int)

