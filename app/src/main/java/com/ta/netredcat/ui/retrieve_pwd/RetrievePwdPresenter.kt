package com.ta.netredcat.ui.retrieve_pwd

import android.content.Context
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.ui.mvp.AppPresenter
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.entity.httpResult.EmptyBean
import com.tianao.module.net.http.ObserverOnNextListener
import io.reactivex.functions.Consumer

class RetrievePwdPresenter(mContext: Context, mView: RetrievePwdView) :
    AppPresenter<RetrievePwdView, RetrievePwdDataManager>() {

    init {
        attachView(mView)
        model = RetrievePwdDataManager(mContext)
    }

    fun requestCheckCode() {
        model!!.requestCheckCode(
            Consumer { },
            AppObserver(object : ObserverOnNextListener<EmptyBean>() {
                override fun onNext(result: AppResult<EmptyBean>?) {
                    view.setBtnLoginEnable(true)
                    ToastUtils.showShort("获取图形验证码")
                }
            })
        )
    }
}