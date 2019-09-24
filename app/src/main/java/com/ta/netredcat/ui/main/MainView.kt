package com.ta.netredcat.ui.main

import com.ta.netredcat.entity.result.HomeDataResult
import com.ta.netredcat.ui.mvp.AppView

interface MainView : AppView {
    fun setBannerData(bannerData: List<HomeDataResult.BannerBean>?)
    fun setMainData(dataList: List<HomeDataResult.CategoryBean>)
    fun setFlipperData(rollLog: List<HomeDataResult.RollLogBean>?)
}