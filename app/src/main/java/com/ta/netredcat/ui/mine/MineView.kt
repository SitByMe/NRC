package com.ta.netredcat.ui.mine

import com.ta.netredcat.entity.result.ServiceBeanResult
import com.ta.netredcat.entity.result.UserInfoResult
import com.ta.netredcat.ui.mvp.AppView

interface MineView : AppView {
    fun setUserInfoData(userInfoData: UserInfoResult)
    fun setServiceData(serviceBeanList: List<ServiceBeanResult>)
}