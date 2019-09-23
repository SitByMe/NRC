package com.ta.netredcat.data.net;

import android.net.ParseException;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonParseException;
import com.ta.netredcat.App;
import com.ta.netredcat.constant.StateConstants;
import com.tianao.module.net.entity.httpResult.AppResult;
import com.tianao.module.net.http.MyObserver;
import com.tianao.module.net.http.NetworkUtils;
import com.tianao.module.net.http.ObserverOnNextListener;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class AppObserver<T> extends MyObserver<T> {
    private boolean showMsg = true;

    public AppObserver(ObserverOnNextListener<T> listener) {
        super(listener);
    }

    public AppObserver(ObserverOnNextListener<T> listener, boolean showMsg) {
        super(listener);
        this.showMsg = showMsg;
    }

    @Override
    public void onNext(AppResult<T> result) {
        if (result.status == StateConstants.SUCCESS) {
            super.onNext(result);
        } else if (result.status == StateConstants.TOKEN_ERROR) {
            showToast("您的账号已在其他地方登录");
            App.mApp.logOut();
        } else if (result.status == StateConstants.LOGIN_OVERDUE) {
            showToast(result.msg);
            App.mApp.logOut();
        } else {
            onOtherNext(result);
        }
    }

    @Override
    public void onOtherNext(AppResult<T> result) {
        if (result.status == StateConstants.FAILED) {
            Log.e("==========", result.toString());
            if (showMsg) {
                showToast(result.msg);
            }
        }
        super.onOtherNext(result);
    }

    @Override
    public void onError(Throwable e) {
        String message;
        if (!NetworkUtils.isConnected()) {
            message = "没有网络";
        /*} else if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            int code = exception.code();
            if (code == 444) {
                message = "请先登录";
                HttpUtils.contextMethod("logOut");
            } else {
                Log.e("HTTP", "http错误码：" + ((HttpException) e).code());
            }*/
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
            message = "链接异常";
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            message = "数据解析失败";
        } else if (e instanceof UnknownHostException) {
            message = "域名解析失败";
        } else {
            message = "请求失败";
        }
        if (TextUtils.isEmpty(message)) {
            message = e.getMessage();
        }
        showToast(message);
        e.printStackTrace();
        super.onError(e);
    }

    private void showToast(String message) {
        if (TextUtils.isEmpty(message)) {
            ToastUtils.showShort("服务器异常");
        } else {
            ToastUtils.showShort(message);
        }
    }
}
