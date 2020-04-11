package com.lielle.duoduoeyepetizer.modulehot

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

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/1
 */
class RankAdapter(context: Context?) : RecyclerView.Adapter<RankAdapter.RankHolder>() {

    var mContext: Context? = context
    var mList: ArrayList<ItemData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.item_hot_data, parent, false)
        return RankHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: RankHolder, position: Int) {
        var data = mList[position]
        holder.bind(data)
    }

    fun updateList(list: java.util.ArrayList<ItemData>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }


    inner class RankHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var iv_photo: ImageView = itemView.findViewById(R.id.iv_photo) as ImageView
        var tv_title: TextView = itemView.findViewById(R.id.tv_title) as TextView
        var tv_time: TextView = itemView.findViewById(R.id.tv_time) as TextView

        fun bind(data: ItemData) {
            Glide.with(mContext).load(data.cover?.feed).into(iv_photo)
            tv_title.text = data.title

            var minute =data.duration?.div(60)
            var second = data.duration?.minus((minute?.times(60)) as Long )
            var realMinute : String
            var realSecond : String
            if(minute!!<10){
                realMinute = "0"+minute
            }else{
                realMinute = minute.toString()
            }
            if(second!!<10){
                realSecond = "0"+second
            }else{
                realSecond = second.toString()
            }
            tv_time.text = "${data.category} / ${realMinute}'${realSecond}''"
        }

    }

}