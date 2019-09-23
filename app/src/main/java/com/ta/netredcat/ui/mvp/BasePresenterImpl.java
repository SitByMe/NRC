package com.ta.netredcat.ui.mvp;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

interface BasePresenterImpl<V extends AppView> extends LifecycleObserver {

    void attachView(V view);

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onViewCreate();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void detachView();

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onLifeCycleChange(@NonNull LifecycleOwner owner,
                           @NonNull Lifecycle.Event event);

    boolean hasLogin();

    void logout();
}
