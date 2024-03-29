package com.tianao.module.net.http;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BaseHttpMethods {
    /**
     * 封装线程管理和订阅的过程
     */
    public static <T> void httpSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 封装线程管理和订阅的过程
     */
    public static <T> void httpSubscribe(
            Observable<T> observable,
            Consumer<Disposable> before,
            Observer<T> observer
    ) {
        if (before == null) {
            httpSubscribe(observable, observer);
        } else {
            observable.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .doOnSubscribe(before)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }
}
