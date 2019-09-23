package com.ta.netredcat.ui.cate_prod

import com.ta.netredcat.entity.SubCateResult
import com.ta.netredcat.ui.mvp.AppView

interface CateProdView : AppView {
    fun setHeadRightText(mustSeeContent: String)
    fun setInputData(dataList: List<SubCateResult.InputKeywordsNew>)
    fun setContentData(dataList: List<SubCateResult.Data>)
}