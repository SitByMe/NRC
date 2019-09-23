package com.ta.netredcat.ui

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.ta.netredcat.R
import com.ta.netredcat.view.TitleBar

abstract class BaseTitleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootContainer: FrameLayout = findViewById(R.id.fl_container)
        val mTitleBar: TitleBar = findViewById(R.id.title_bar)
        mTitleBar.tvTitle.text = getTitleText()
        mTitleBar.ivLeft.setOnClickListener { finish() }
        mTitleBar.activity = this
        val view: View = View.inflate(this, getContentLayoutId(), null)
        rootContainer.addView(view)
    }

    abstract fun getTitleText(): String

    override fun setContentLayout(): Int = R.layout.activity_basetitle

    abstract fun getContentLayoutId(): Int
}