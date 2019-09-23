package com.ta.netredcat.view

import android.view.View

class ClickProxy(private val originListener: View.OnClickListener) : View.OnClickListener {
    private var lastClick: Long = 0
    private var times: Long = 1500

    constructor(originListener: View.OnClickListener, times: Int) : this(originListener) {
        this.times = times.toLong()
    }

    override fun onClick(v: View) {
        if (originListener !is ClickProxy && System.currentTimeMillis() - lastClick >= times) {
            originListener.onClick(v)
            lastClick = System.currentTimeMillis()
        }
    }
}
