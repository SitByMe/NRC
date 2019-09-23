package com.ta.netredcat.ui.retrieve_pwd

import com.ta.netredcat.ui.mvp.AppView

interface RetrievePwd2View : AppView {
    fun setBtnVerifyCodeEnable(enable: Boolean)
    fun setBtnVerifyCodeText(text: String)
    fun setBtnLoginEnable(enable: Boolean)
}