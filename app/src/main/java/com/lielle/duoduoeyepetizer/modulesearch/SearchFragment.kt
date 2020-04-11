package com.lielle.duoduoeyepetizer.modulesearch

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.lielle.duoduoeyepetizer.util.KeyBoardUtils
import com.lielle.duoduoeyepetizer.R
import com.lielle.duoduoeyepetizer.modulesearch.result.ResultActivity

import kotlinx.android.synthetic.main.fragment_search_layout.*

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/2
 */

const val SEARCH_TAG = "SearchFragment"

class SearchFragment : DialogFragment(), View.OnClickListener, CircularRevealAnim.AnimListener,
    ViewTreeObserver.OnPreDrawListener, SearchTagAdapter.DialogDismissListener {

    lateinit var mView: View

    var data: ArrayList<String> = arrayListOf(
        "脱口秀", "城会玩", "666", "笑cry", "漫威",
        "清新", "匠心", "VR", "心理学", "舞蹈", "品牌广告", "粉丝自制", "电影相关", "萝莉", "魔性"
        , "第一视角", "教程", "毕业设计", "奥斯卡", "燃", "冰与火之歌", "温情", "线下campaign", "公益"
    )
    lateinit var mAdapter: SearchTagAdapter

    lateinit var mCircularRevealAnim: CircularRevealAnim

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            DialogFragment.STYLE_NO_FRAME,
            R.style.DialogStyle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_search_layout, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init recyclerview
        var layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        mAdapter = SearchTagAdapter(context!!, data)
        mAdapter.setListener(this)
        recyclerView.adapter = mAdapter
        //init search icon
        iv_search_search.setOnClickListener(this)
        iv_search_back.setOnClickListener(this)
        //init close animation
        mCircularRevealAnim = CircularRevealAnim()
        mCircularRevealAnim.setAnimListener(this)
        //init animation
        iv_search_search.viewTreeObserver.addOnPreDrawListener(this)
    }

    override fun onStart() {
        super.onStart()
        initDialog()
    }

    private fun initDialog() {
        val window = dialog?.window
        val metrics = resources.displayMetrics
        val width = (metrics.widthPixels * 0.98).toInt()
        window?.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT)
        window?.setGravity(Gravity.TOP)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_search_back -> exitSearch()

            R.id.iv_search_search -> search()
        }
    }

    private fun search() {
        val keyWords = et_search_keyword.text.toString()
        if (TextUtils.isEmpty(keyWords)) {
            Toast.makeText(context, "未输入关键字", Toast.LENGTH_SHORT).show()
        } else {
            exitSearch()
            //start serach result activity
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra(ResultActivity.key, keyWords)
            context?.startActivity(intent)
        }
    }

    private fun exitSearch() {
        KeyBoardUtils.closeKeyBoard(context!!, et_search_keyword);
        mCircularRevealAnim.hide(iv_search_search, mView)
    }

    override fun onHideAnimationEnd() {
        //clear search text
        et_search_keyword.setText("");
        // dismiss dialog
        dismiss()
    }

    override fun onShowAnimationEnd() {
        // do nothing yet
    }

    override fun onPreDraw(): Boolean {
        iv_search_search.viewTreeObserver.removeOnPreDrawListener(this)
        mCircularRevealAnim.show(iv_search_search, mView)
        return true
    }

    override fun onDismiss() {
        exitSearch()
    }
}