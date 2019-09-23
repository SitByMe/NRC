package com.ta.netredcat.ui.retrieve_pwd

import android.content.Context
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.ui.mvp.AppDataManager
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.entity.httpResult.EmptyBean
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class RetrievePwdDataManager(mContext: Context) : AppDataManager(mContext, null, null, null) {
    fun requestCheckCode(
        before: Consumer<Disposable>,
        observer: AppObserver<EmptyBean>
    ) {
        val result: AppResult<EmptyBean> = AppResult()
        val bean = EmptyBean()
        result.status = 200
        result.msg = "MESSAGE"
        result.data = bean
        observer.onNext(result)
    }
}