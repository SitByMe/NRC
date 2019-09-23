package com.ta.netredcat.ui.retrieve_pwd

import com.ta.netredcat.ui.mvp.AppView

interface RetrievePwdView : AppView {
    fun setBtnLoginEnable(enable: Boolean)
}