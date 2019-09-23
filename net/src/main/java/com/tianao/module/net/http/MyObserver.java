package com.tianao.module.net.http;

import com.tianao.module.net.entity.httpResult.AppResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyObserver<T> implements Observer<AppResult<T>>, ProgressCancelListener {
    private static final String TAG = "ProgressObserver";
    private ObserverOnNextListener<T> listener;
//    private Disposable d;

    public MyObserver(ObserverOnNextListener<T> listener) {
        this.listener = listener;
    }

    public ObserverOnNextListener<T> getListener() {
        return listener;
    }

    @Override
    public void onSubscribe(Disposable d) {
//        this.d = d;
        if (listener != null) {
            listener.onSubscribe(d);
        }
    }

    @Override
    public void onNext(AppResult<T> result) {
        if (listener != null) {
            listener.onNext(result);
            listener.onCompleted();
        }
    }

    public void onOtherNext(AppResult<T> result) {
        if (listener != null) {
            listener.onOtherNext(result);
            listener.onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (listener != null) listener.onError(e);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onCancelProgress() {
        //如果处于订阅状态，则取消订阅
       /* if (!d.isDisposed()) {
            d.dispose();
        }*/
    }
}
