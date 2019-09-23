package com.ta.netredcat.view.et

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes

import com.ta.netredcat.R
import kotlinx.android.synthetic.main.widget_layout_phone_edit_text.view.*

class PhoneEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var root: View

    init {
        initView()
    }

    private fun initView() {
        root = LayoutInflater.from(context)
            .inflate(R.layout.widget_layout_phone_edit_text, this, false)
        addView(root)
    }

    fun getText(): String = root.et_user_name.text.toString()
}
