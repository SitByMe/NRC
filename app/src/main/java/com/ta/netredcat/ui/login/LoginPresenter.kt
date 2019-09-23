package com.ta.netredcat.ui.login

import android.content.Context
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.constant.SpConstants
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.data.net.method.UserHttpMethods
import com.ta.netredcat.entity.LoginResult

import com.ta.netredcat.ui.mvp.AppPresenter
import com.ta.netredcat.utils.SpUtils
import com.ta.netredcat.utils.helper.SpHelper
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.http.ObserverOnNextListener
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class LoginPresenter(mContext: Context, mView: LoginView) :
    AppPresenter<LoginView, LoginDataManager>() {
    var dis: Disposable? = null

    init {
        attachView(mView)
        model = LoginDataManager(mContext)
    }

    override fun detachView() {
        super.detachView()
        if (dis != null && !dis!!.isDisposed) {
            dis!!.dispose()
            dis = null
        }
    }

    fun login(
        userName: String,
        verifyCode: String,
        invCode: String
    ) {
        UserHttpMethods.login(
            userName,
            verifyCode,
            invCode,
            Consumer { view.showLoadingView() },
            AppObserver(object : ObserverOnNextListener<LoginResult>() {
                override fun onCompleted() {
                    super.onCompleted()
                    view.dismissLoadingView()
                }

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    view.dismissLoadingView()
                }

                override fun onNext(result: AppResult<LoginResult>) {
                    ToastUtils.showShort("登录成功")
                    SpHelper.putUserInfo(result)
                    view.finishActivity()
                }
            })
        )
    }
}
