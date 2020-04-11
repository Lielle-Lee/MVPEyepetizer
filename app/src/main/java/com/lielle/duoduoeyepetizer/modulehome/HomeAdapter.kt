package com.lielle.duoduoeyepetizer.modulehome

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lielle.duoduoeyepetizer.DetailActivity
import com.lielle.duoduoeyepetizer.R
import com.lielle.duoduoeyepetizer.modulehome.HomeAdapter.*
import com.lielle.duoduoeyepetizer.model.bean.ItemBean
import com.lielle.duoduoeyepetizer.model.bean.VideoBean
import java.util.ArrayList

/**
 *
 * @des home页数据的adapter
 * @author lielleli
 * @time 2020/3/26
 */
class HomeAdapter(context: Context?) : RecyclerView.Adapter<HomeDataHolder>() {

    var context: Context? = null
    var list: ArrayList<ItemBean> = ArrayList()

    init {
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDataHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_home_data, parent, false)
        return HomeDataHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeDataHolder, position: Int) {
        var itemBean = list[position]
        holder.bindData(itemBean)
        holder.itemView.setOnClickListener {
            //构造video bean 并跳转详情界面
            val feed = itemBean.data.cover?.feed
            val title = itemBean.data.title
            val des = itemBean.data.des
            val duration = itemBean.data.duration
            val playUrl = itemBean.data.playUrl
            val category = itemBean.data.category
            val blurred = itemBean.data.cover?.blurred
            val collecNum = itemBean.data.consumption?.collectionCount
            val shareNum = itemBean.data.consumption?.shareCount
            val replyNum = itemBean.data.consumption?.replyCount
            val time = System.currentTimeMillis()
            val dataBean = VideoBean(
                feed,
                title,
                des,
                duration,
                playUrl,
                category,
                blurred,
                collecNum,
                shareNum,
                replyNum,
                time
            )
            //start activity
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.dataKey, dataBean as Parcelable)
            context?.startActivity(intent)
        }

    }

    fun updateData(dataList: ArrayList<ItemBean>) {
        Log.d("Lielle", "${dataList.size}")
        list.clear()
        list.addAll(dataList)
        notifyDataSetChanged()
    }


    inner class HomeDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv_detail: TextView
        var tv_title: TextView

        var tv_photo: ImageView
        var tv_user: ImageView

        init {
            tv_detail = itemView.findViewById(R.id.tv_detail)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_photo = itemView.findViewById(R.id.iv_photo)
            tv_user = itemView.findViewById(R.id.iv_user)
        }

        fun bindData(itemBean: ItemBean?) {
            tv_title.text = itemBean?.data?.title
            tv_detail.text = itemBean?.data?.category
            //使用图片加载框架
            Glide.with(context).load(itemBean?.data?.cover?.feed).into(tv_photo)
            val author = itemBean?.data?.author
            if (author != null) {
                tv_user.visibility = View.VISIBLE
                Glide.with(context).load(author.icon).into(tv_user)
            } else {
                tv_user.visibility = View.GONE
            }
            //TODO 暂时不处理点击逻辑
        }
    }
}