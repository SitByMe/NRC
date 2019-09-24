package com.ta.netredcat.ui.feed_back.my_feed

import android.content.Context

import com.ta.netredcat.entity.result.MyFeedBackResult
import com.ta.netredcat.ui.mvp.AppPresenter
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.http.ObserverOnNextListener

import io.reactivex.functions.Consumer

class FeedPresenter(context: Context, feedBackView: FeedBackView) :
    AppPresenter<FeedBackView, FeedBackDateManager>() {

    init {
        attachView(feedBackView)
        model = FeedBackDateManager(context)
    }

    internal fun requestMyFeedBack() {
        model!!.requestMyFeedBack(
            Consumer {
                view.showLoadingView()
            },
            object : ObserverOnNextListener<MyFeedBackResult>() {
                override fun onCompleted() {
                    super.onCompleted()
                    view.dismissLoadingView()
                }

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    view.dismissLoadingView()
                }

                override fun onNext(result: AppResult<MyFeedBackResult>) {
                    view.setDate(result.data.list)
                }
            })
    }
}
