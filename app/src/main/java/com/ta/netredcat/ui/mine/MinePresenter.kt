package com.ta.netredcat.ui.mine

import android.content.Context
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.entity.result.ServiceBeanResult
import com.ta.netredcat.entity.result.UserInfoResult
import com.ta.netredcat.ui.mvp.AppPresenter
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.http.ObserverOnNextListener
import io.reactivex.functions.Consumer

class MinePresenter(mContext: Context, mView: MineView) :
    AppPresenter<MineView, MineDataManager>() {
    init {
        attachView(mView)
        model = MineDataManager(mContext)
    }

    fun requestUserInfo() {
        model!!.requestUserInfo(
            Consumer { },
            AppObserver(object : ObserverOnNextListener<UserInfoResult>() {
                override fun onNext(result: AppResult<UserInfoResult>) {
                    view.setUserInfoData(result.data)
                }
            })
        )
    }

    fun requestCustomerServiceList() {
        model!!.requestCustomerServiceList(
            Consumer { },
            AppObserver(object : ObserverOnNextListener<List<ServiceBeanResult>>() {
                override fun onNext(result: AppResult<List<ServiceBeanResult>>) {
                    view.setServiceData(result.data)
                }
            })
        )
    }
}