package com.lielle.duoduoeyepetizer.modulefind

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lielle.duoduoeyepetizer.R
import com.lielle.duoduoeyepetizer.model.bean.FindBean

/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/30
 */
class FindAdapter(context: Context?) : RecyclerView.Adapter<FindAdapter.FindHolder>() {

    var mContext: Context? = null
    var mList: MutableList<FindBean> = ArrayList()

    init {
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.item_discover_data, parent, false)
        return FindHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: FindHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)
    }

    fun updateData(dataList: MutableList<FindBean>) {
        this.mList.clear()
        this.mList.addAll(dataList)
        notifyDataSetChanged()
    }

    inner class FindHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var iv_photo: ImageView
        var tv_title: TextView

        init {
            tv_title = itemView.findViewById(R.id.tv_title) as TextView
            iv_photo = itemView.findViewById(R.id.iv_photo) as ImageView
        }

        fun bind(data: FindBean) {
            tv_title.text = data.name
            Glide.with(mContext).load(data.bgPic).into(iv_photo)
        }

    }
}