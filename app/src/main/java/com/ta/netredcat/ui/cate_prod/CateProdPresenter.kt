package com.ta.netredcat.ui.cate_prod

import android.content.Context
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.entity.HomeDataResult
import com.ta.netredcat.entity.SubCateResult
import com.ta.netredcat.ui.mvp.AppPresenter
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.http.ObserverOnNextListener
import io.reactivex.functions.Consumer

class CateProdPresenter(mContext: Context, mView: CateProdView) :
    AppPresenter<CateProdView, CateProdDataManager>() {
    init {
        attachView(mView)
        model = CateProdDataManager(mContext)
    }

    fun requestCateProdData(cateId: String) {
        model!!.requestCateProdData(
            cateId,
            Consumer { },
            AppObserver(object : ObserverOnNextListener<SubCateResult>() {
                override fun onNext(result: AppResult<SubCateResult>?) {
                    println(result.toString())
                    result?.data?.must_see_content?.let { view.setHeadRightText(it) }
                    result?.data?.input_keywords_new?.let { view.setInputData(it) }
                    result?.data?.data_list?.let { view.setContentData(it) }
                }
            })
        )
    }
}