package com.ta.netredcat.ui.register

import android.content.Context
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.data.net.method.UserHttpMethods
import com.ta.netredcat.ui.mvp.AppDataManager
import com.tianao.module.net.entity.httpResult.EmptyBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import okhttp3.ResponseBody

class Register2DataManager(mContext: Context) : AppDataManager(mContext, null, null, null) {
    fun requestCheckCode(
        before: Consumer<Disposable>,
        observer: Observer<ResponseBody>
    ) {
/*
        val result: AppResult<EmptyBean> = AppResult()
        val bean = EmptyBean()
        result.status = 200
        result.msg = "MESSAGE"
        result.data = bean
        observer.onNext(result)
*/

        UserHttpMethods.requestCheckCode(
            DeviceUtils.getUniqueDeviceId(),
            System.currentTimeMillis().toString(),
            before,
            observer
        )
    }
}