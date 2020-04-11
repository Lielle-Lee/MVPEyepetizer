package com.lielle.duoduoeyepetizer.modulesearch.result

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lielle.duoduoeyepetizer.R
import com.lielle.duoduoeyepetizer.model.bean.ItemData
import java.text.SimpleDateFormat

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/3
 */
class SearchResultAdapter(context: Context?) :
    RecyclerView.Adapter<SearchResultAdapter.ResultHolder>() {

    var mContext = context
    var mList: ArrayList<ItemData> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val itemView =
            LayoutInflater.from(mContext).inflate(R.layout.item_search_result, parent, false)
        return ResultHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        val item = mList[position]
        holder.bind(item)
    }

    fun updateList(list: java.util.ArrayList<ItemData>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var iv_photo: ImageView = itemView.findViewById(R.id.iv_photo) as ImageView
        var tv_title: TextView = itemView.findViewById(R.id.tv_title) as TextView
        var tv_time: TextView = itemView.findViewById(R.id.tv_detail) as TextView

        fun bind(item: ItemData) {
            Glide.with(mContext).load(item.cover?.feed).into(iv_photo)
            tv_title.text = item.title
            var category = item.category
            var duration = item.duration
            var minute = duration?.div(60)
            var second = duration?.minus((minute?.times(60)) as Long)
            var releaseTime = item.releaseTime
            var smf: SimpleDateFormat = SimpleDateFormat("MM-dd")
            var date = smf.format(releaseTime)
            var realMinute: String
            var realSecond: String
            if (minute!! < 10) {
                realMinute = "0" + minute
            } else {
                realMinute = minute.toString()
            }
            if (second!! < 10) {
                realSecond = "0" + second
            } else {
                realSecond = second.toString()
            }
            tv_time.text = "$category / $realMinute'$realSecond'' / $date"
        }

    }
}