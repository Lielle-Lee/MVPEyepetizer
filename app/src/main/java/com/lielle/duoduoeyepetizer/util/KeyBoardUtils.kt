package com.lielle.duoduoeyepetizer.util

import android.content.Context
import android.hardware.input.InputManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 *
 * @des
 * @author lielleli
 * @time 2020/4/2
 */

object KeyBoardUtils {

    fun closeKeyBoard(context: Context, editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}