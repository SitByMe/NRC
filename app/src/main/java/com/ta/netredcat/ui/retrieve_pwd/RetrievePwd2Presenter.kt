package com.ta.netredcat.ui.retrieve_pwd

import android.content.Context
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.data.net.method.UserHttpMethods
import com.ta.netredcat.ui.mvp.AppPresenter
import com.ta.netredcat.utils.helper.CountDownHelper
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.entity.httpResult.EmptyBean
import com.tianao.module.net.http.ObserverOnNextListener
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class RetrievePwd2Presenter(mContext: Context, mView: RetrievePwd2View) :
    AppPresenter<RetrievePwd2View, RetrievePwd2DataManager>() {

    var dis: Disposable? = null

    init {
        attachView(mView)
        model = RetrievePwd2DataManager(mContext)
    }

    fun sendVerifyCode(
        userName: String,
        code: String
    ) {
        UserHttpMethods.sendCode(
            userName, code, 0.toString(), Consumer {
                view.setBtnVerifyCodeEnable(false)
                view.showLoadingView()
            }, AppObserver(object : ObserverOnNextListener<EmptyBean>() {
                override fun onCompleted() {
                    super.onCompleted()
                    view.dismissLoadingView()
                }

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    view.setBtnVerifyCodeText("重新获取")
                    view.setBtnVerifyCodeEnable(true)
                    view.dismissLoadingView()
                }

                override fun onNext(result: AppResult<EmptyBean>) {
                    view.setBtnLoginEnable(true)
                    dis = CountDownHelper.startCountDown(Consumer {
                        if (it != 60L) {
                            view.setBtnVerifyCodeText("计时:" + (60 - it))
                            println("计时：$it")
                        } else {
                            if (!dis!!.isDisposed) {
                                dis!!.dispose()
                            }
                            view.setBtnVerifyCodeText("重新获取")
                            view.setBtnVerifyCodeEnable(true)
                        }
                    })
                }

                override fun onOtherNext(result: AppResult<EmptyBean>?) {
                    super.onOtherNext(result)
                    view.setBtnVerifyCodeEnable(true)
                }
            })
        )
    }
}