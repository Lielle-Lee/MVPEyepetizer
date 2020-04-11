package com.lielle.duoduoeyepetizer.modulesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lielle.duoduoeyepetizer.R
import com.lielle.duoduoeyepetizer.modulesearch.result.ResultActivity

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/3
 */
class SearchTagAdapter(context: Context, datalist: ArrayList<String>) :
    RecyclerView.Adapter<SearchTagAdapter.TagHolder>() {

    var mContext: Context = context
    var mDataList: ArrayList<String> = datalist
    var mListener: DialogDismissListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagHolder {
        var itemView =
            LayoutInflater.from(mContext).inflate(R.layout.item_search_tag, parent, false)
        return TagHolder(itemView)
    }

    override fun getItemCount(): Int = mDataList.size

    override fun onBindViewHolder(holder: TagHolder, position: Int) {
        holder.tagTextView.text = mDataList[position]
        holder.itemView.setOnClickListener {
            var keyWords = mDataList[position]
            var intent = Intent(mContext, ResultActivity::class.java)
            intent.putExtra(ResultActivity.key, keyWords)
            mContext.startActivity(intent)
            mListener?.onDismiss()
        }

    }


    inner class TagHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tagTextView: TextView = itemView.findViewById(R.id.tv_title) as TextView
    }

    interface DialogDismissListener {
        fun onDismiss()
    }

    public fun setListener(listener: DialogDismissListener) {
        mListener = listener
    }
}