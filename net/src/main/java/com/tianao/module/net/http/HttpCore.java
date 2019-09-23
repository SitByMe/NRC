package com.tianao.module.net.http;

import com.tianao.module.net.BuildConfig;
import com.tianao.module.net.convert.ConverterFactory;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.RxDialogFragment;
import com.trello.rxlifecycle2.components.RxFragment;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public abstract class HttpCore {

    private static <S> S createRetrofitForGsonConverter(Class<S> iServer, String apiHost,
                                                        boolean hasLogin, String token, String uid) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLogInterceptor());//使用自定义的Log拦截器
//            builder.addInterceptor(new LoggingInterceptor());//使用自定义的Log拦截器
        }

        builder.addInterceptor(addHeader(hasLogin, token, uid))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(apiHost);
        if (BuildConfig.USE_DECODE) {
            retrofitBuilder.addConverterFactory(ConverterFactory.create());
        } else {
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create());//请求的结果转为实体类
        }
        Retrofit retrofit = retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        return retrofit.create(iServer);
    }

    private static <S> S createRetrofitForStringConverter(Class<S> iServer, String apiHost,
                                                          boolean hasLogin, String token, String uid, boolean resultIsObject) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLogInterceptor());//使用自定义的Log拦截器
//            builder.addInterceptor(new LoggingInterceptor());//使用自定义的Log拦截器
        }

        builder.addInterceptor(addHeader(hasLogin, token, uid))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(apiHost);
        if (!resultIsObject) {
            retrofitBuilder.addConverterFactory(ScalarsConverterFactory.create());
        } else if (BuildConfig.USE_DECODE) {
            retrofitBuilder.addConverterFactory(ConverterFactory.create());
        } else {
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create());//请求的结果转为实体类
        }
        Retrofit retrofit = retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        return retrofit.create(iServer);
    }

    public static <S> S getService(Class<S> iServer, String apiHost, boolean hasLogin, String token, String uid) {
        return createRetrofitForGsonConverter(iServer, apiHost, hasLogin, token, uid);
    }

    public static <S> S getService(Class<S> iServer, String apiHost, boolean hasLogin, String token, String uid, boolean resultIsObject) {
        if (resultIsObject) {
            return createRetrofitForGsonConverter(iServer, apiHost, hasLogin, token, uid);
        } else {
            return createRetrofitForStringConverter(iServer, apiHost, hasLogin, token, uid, false);
        }
    }

    private static Interceptor addHeader(final boolean hasLogin, final String token, final String uid) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder();
                if (hasLogin) {
                    request.addHeader("token", token);
                    request.addHeader("uid", uid);
                }
                return chain.proceed(request.build());
            }
        };
    }

    /**
     * Http请求的Observable处理
     * 1> 处理线程调度；
     * 2> 生命周期与组件生命周期绑定；
     *
     * @return
     */
    public static <T> ObservableTransformer<T, T> handleObservable(final Object component) {
        WeakReference<Object> srComponent = new WeakReference<>(component);
        LifecycleTransformer<T> lifecycleTransformer;
        if (component instanceof RxAppCompatActivity) {
            lifecycleTransformer = ((RxAppCompatActivity) srComponent.get()).bindToLifecycle();
        } else if (component instanceof RxFragment) {
            lifecycleTransformer = ((RxFragment) srComponent.get()).bindToLifecycle();
        } else if (component instanceof RxDialogFragment) {
            lifecycleTransformer = ((RxDialogFragment) srComponent.get()).bindToLifecycle();
        } else {
            throw new NullPointerException("lifecycleTransformer is not null");
        }
        final LifecycleTransformer<T> bindLifecycle = lifecycleTransformer;
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.compose(bindLifecycle)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io());
            }
        };
    }
}