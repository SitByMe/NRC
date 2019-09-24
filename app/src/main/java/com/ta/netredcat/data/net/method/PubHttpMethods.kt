package com.ta.netredcat.data.net.method

import com.ta.netredcat.constant.Constants
import com.ta.netredcat.data.net.HttpCoreHelper
import com.ta.netredcat.data.net.iservice.IPubService
import com.ta.netredcat.entity.result.HomeDataResult
import com.ta.netredcat.entity.result.ServiceBeanResult
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.entity.httpResult.EmptyBean
import com.tianao.module.net.http.BaseHttpMethods
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class PubHttpMethods : BaseHttpMethods() {
    companion object {
        fun requestHomeData(
            appVersionName: String,
            before: Consumer<Disposable>,
            observer: Observer<AppResult<HomeDataResult>>
        ) {
            val params = mutableMapOf<String, String>()
            params["level"] = appVersionName
            httpSubscribe(
                HttpCoreHelper.getService(IPubService::class.java).requestHomeData(params.toMap<String, String>()),
                before,
                observer
            )
        }

        fun requestCustomerServiceList(
            before: Consumer<Disposable>,
            observer: Observer<AppResult<List<ServiceBeanResult>>>
        ) {
            httpSubscribe(
                HttpCoreHelper.getService(IPubService::class.java).requestCustomerServiceList(
                    Constants.USELESS
                ),
                before,
                observer
            )
        }

        fun commitFeedBack(
            content: String, type: String, image: String,
            before: Consumer<Disposable>,
            observer: Observer<AppResult<EmptyBean>>
        ) {
            httpSubscribe(
                HttpCoreHelper.getService(IPubService::class.java).commitFeedBack(
                    content,
                    type,
                    image
                ), before, observer
            )
        }
    }
}
