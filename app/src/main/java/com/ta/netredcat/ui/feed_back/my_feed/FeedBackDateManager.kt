package com.ta.netredcat.ui.feed_back.my_feed

import android.content.Context

import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.data.net.method.UserHttpMethods
import com.ta.netredcat.entity.result.MyFeedBackResult
import com.ta.netredcat.ui.mvp.AppDataManager
import com.tianao.module.net.http.ObserverOnNextListener

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class FeedBackDateManager(mContext: Context) : AppDataManager(mContext, null, null, null) {

    fun requestMyFeedBack(
        before: Consumer<Disposable>,
        listener: ObserverOnNextListener<MyFeedBackResult>
    ) {
        UserHttpMethods.requestMyFeedBack(before, AppObserver(listener))
    }
}
