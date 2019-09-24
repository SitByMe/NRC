package com.ta.netredcat.ui.main

import android.content.Context
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.data.net.method.PubHttpMethods
import com.ta.netredcat.entity.result.HomeDataResult
import com.ta.netredcat.ui.mvp.AppDataManager
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class MainDataManager(mContext: Context) : AppDataManager(mContext, null, null, null) {
    fun requestHomeData(
        appVersionName: String,
        before: Consumer<Disposable>,
        observer: AppObserver<HomeDataResult>
    ) {
        PubHttpMethods.requestHomeData(appVersionName, before, observer)
    }
}