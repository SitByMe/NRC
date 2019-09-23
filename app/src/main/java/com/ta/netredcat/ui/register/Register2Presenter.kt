package com.ta.netredcat.ui.register

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.blankj.utilcode.util.PhoneUtils.call
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.data.net.method.UserHttpMethods
import com.ta.netredcat.ui.main.MainActivity
import com.ta.netredcat.ui.mvp.AppPresenter
import com.ta.netredcat.utils.helper.CountDownHelper
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.entity.httpResult.EmptyBean
import com.tianao.module.net.http.ObserverOnNextListener
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.util.HalfSerializer.onNext
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.File

class Register2Presenter(mContext: Context, mView: Register2View) :
    AppPresenter<Register2View, Register2DataManager>() {
    var dis: Disposable? = null

    init {
        attachView(mView)
        model = Register2DataManager(mContext)
    }

    override fun detachView() {
        super.detachView()
        if (dis != null && !dis!!.isDisposed) {
            dis!!.dispose()
            dis = null
        }
    }

    fun requestCheckCode() {
        model!!.requestCheckCode(
            Consumer { },
            object : Observer<ResponseBody> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: ResponseBody) {
                    ToastUtils.showShort("获取图形验证码")
                }

                override fun onError(e: Throwable) {
                }

            }
        )
    }

    fun sendVerifyCode(
        userName: String,
        code: String
    ) {
        UserHttpMethods.sendCode(
            userName, code, 1.toString(), Consumer {
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

    fun register() {}
}