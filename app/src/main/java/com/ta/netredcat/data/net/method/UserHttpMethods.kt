package com.ta.netredcat.data.net.method

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.blankj.utilcode.util.DeviceUtils
import com.ta.netredcat.constant.Constants
import com.ta.netredcat.data.net.HttpCoreHelper
import com.ta.netredcat.data.net.iservice.IUserService
import com.ta.netredcat.entity.LoginResult
import com.ta.netredcat.entity.UserInfoResult
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.entity.httpResult.EmptyBean
import com.tianao.module.net.http.BaseHttpMethods

import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.File

class UserHttpMethods : BaseHttpMethods() {
    companion object {

        fun requestCheckCode(
            deviceId: String, time: String, before: Consumer<Disposable>,
            observer: Observer<ResponseBody>
        ) {
           /* HttpCoreHelper.getService(IUserService::class.java).requestCheckCode(deviceId, time)
                .subscribeOn(Schedulers.newThread())
                .map(
                    Function<ResponseBody, Bitmap> {
                        var bitmap1: Bitmap! = BitmapFactory.decodeStream(it.byteStream())

                    }
                )*/

            httpSubscribe(
                HttpCoreHelper.getService(IUserService::class.java, false).requestCheckCode(
                    deviceId,
                    time
                ), before, observer
            )
        }

        fun requestUserInfo(
            before: Consumer<Disposable>,
            observer: Observer<AppResult<UserInfoResult>>
        ) {
            httpSubscribe(
                HttpCoreHelper.getService(IUserService::class.java).requestUserInfo(Constants.USELESS),
                before,
                observer
            )
        }

        fun sendCode(
            phone: String,
            code: String,
            type: String,
            before: Consumer<Disposable>,
            observer: Observer<AppResult<EmptyBean>>
        ) {
            httpSubscribe(
                HttpCoreHelper.getService(IUserService::class.java).sendCode(
                    phone, code, DeviceUtils.getUniqueDeviceId(), type
                ), before, observer
            )
        }

        fun login(
            userName: String,
            verifyCode: String,
            invCode: String,
            before: Consumer<Disposable>,
            observer: Observer<AppResult<LoginResult>>
        ) {
            if (invCode.isEmpty()) {
                httpSubscribe(
                    HttpCoreHelper.getService(IUserService::class.java).login(
                        userName, verifyCode
                    ), before, observer
                )
            } else {
                httpSubscribe(
                    HttpCoreHelper.getService(IUserService::class.java).login(
                        userName, verifyCode, invCode
                    ), before, observer
                )
            }
        }
    }
}
