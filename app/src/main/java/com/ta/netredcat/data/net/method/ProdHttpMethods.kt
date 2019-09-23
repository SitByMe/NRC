package com.ta.netredcat.data.net.method

import com.ta.netredcat.data.net.HttpCoreHelper
import com.ta.netredcat.data.net.iservice.IProdService
import com.ta.netredcat.entity.SubCateResult
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.http.BaseHttpMethods
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class ProdHttpMethods : BaseHttpMethods() {
    companion object {
        fun requestCateProdData(
            cateId: String,
            before: Consumer<Disposable>,
            observer: Observer<AppResult<SubCateResult>>
        ) {
            httpSubscribe(
                HttpCoreHelper.getService(IProdService::class.java).requestCateProdData(
                    "0",
                    cateId
                ), before, observer
            )
        }
    }
}