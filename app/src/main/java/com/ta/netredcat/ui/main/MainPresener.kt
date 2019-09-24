package com.ta.netredcat.ui.main

import android.content.Context
import com.blankj.utilcode.util.AppUtils
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.entity.result.HomeDataResult
import com.ta.netredcat.ui.mvp.AppPresenter
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.http.ObserverOnNextListener
import io.reactivex.functions.Consumer

class MainPresener(mContext: Context, mView: MainView) :
    AppPresenter<MainView, MainDataManager>() {
    init {
        attachView(mView)
        model = MainDataManager(mContext)
    }

    fun requestHomeData() {
        model!!.requestHomeData(
            AppUtils.getAppVersionName(),
            Consumer { view.showLoadingView() },
            AppObserver(object : ObserverOnNextListener<HomeDataResult>() {
                override fun onCompleted() {
                    super.onCompleted()
                    view.dismissLoadingView()
                }

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    view.dismissLoadingView()
                }

                override fun onNext(result: AppResult<HomeDataResult>?) {
                    view.setFlipperData(result!!.data.rollLog)
                    view.setBannerData(result.data.banner)
                    view.setMainData(result.data.category!!)
                }
            })
        )
    }
}