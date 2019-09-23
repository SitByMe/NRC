package com.ta.netredcat.ui.mine

import android.content.Context
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.data.net.method.PubHttpMethods
import com.ta.netredcat.data.net.method.UserHttpMethods
import com.ta.netredcat.entity.ServiceBeanResult
import com.ta.netredcat.entity.UserInfoResult
import com.ta.netredcat.ui.mvp.AppDataManager
import com.tianao.module.net.entity.httpResult.AppResult
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class MineDataManager(mContext: Context) : AppDataManager(mContext, null, null, null) {

    fun requestUserInfo(
        before: Consumer<Disposable>,
        observer: AppObserver<UserInfoResult>
    ) {
        UserHttpMethods.requestUserInfo(before, observer)
    }

    fun requestCustomerServiceList(
        before: Consumer<Disposable>,
        observer: AppObserver<List<ServiceBeanResult>>
    ) {
        PubHttpMethods.requestCustomerServiceList(before, observer)
    }
}