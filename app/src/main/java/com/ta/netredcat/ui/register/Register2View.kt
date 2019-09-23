package com.ta.netredcat.ui.register

import com.ta.netredcat.ui.mvp.AppView

interface Register2View : AppView {
    fun setBtnVerifyCodeEnable(enable: Boolean)
    fun setBtnVerifyCodeText(text: String)
    fun setBtnLoginEnable(enable: Boolean)
}