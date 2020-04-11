package com.lielle.duoduoeyepetizer.modulefind

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 *
 * @des
 * @author lielleli
 * @time 2020/3/30
 */
class SpacesItemDecoration(val context: Context, val space: Int = 10) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(space, space, space, space);
    }
}