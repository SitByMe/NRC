package com.ta.netredcat.utils.helper

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

object CountDownHelper {
    fun startCountDown(consumer: Consumer<Long>): Disposable {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
            .unsubscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(consumer)
    }

    interface IConsumer {
        fun onNext(time: Long)
        fun completed()
    }
}
