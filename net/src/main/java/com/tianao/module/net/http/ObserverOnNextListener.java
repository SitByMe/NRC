package com.tianao.module.net.http;

import com.tianao.module.net.entity.httpResult.AppResult;

import io.reactivex.disposables.Disposable;

public abstract class ObserverOnNextListener<T> {
    public void onSubscribe(Disposable d) {

    }

    public void onCompleted() {
    }

    public void onError(Throwable e) {
        e.printStackTrace();
    }

    public abstract void onNext(AppResult<T> result);

    public void onOtherNext(AppResult<T> result) {
    }
}
