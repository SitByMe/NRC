package com.ta.netredcat.ui.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

import com.blankj.utilcode.util.LogUtils

import java.lang.ref.WeakReference
import java.util.ArrayList

import io.reactivex.disposables.Disposable

open class AppPresenter<V : AppView, M : DataManagerImpl> : BasePresenterImpl<V> {
    private var mView: WeakReference<V>? = null
    protected var model: M? = null
    private val mDisposableList = ArrayList<Disposable>()

    val view: V
        get() = mView!!.get()!!

    override fun attachView(view: V) {
        this.mView = WeakReference(view)
    }

    override fun onViewCreate() {
        LogUtils.eTag("View创建")
    }

    override fun detachView() {
        var i = 0
        val size = mDisposableList.size
        while (i < size) {
            val disposed = mDisposableList.get(i)
            LogUtils.eTag("销毁了！！！！！！！！", disposed.hashCode())
            disposed.dispose()
            i++
        }
    }

    override fun onLifeCycleChange(owner: LifecycleOwner, event: Lifecycle.Event) {
        LogUtils.eTag("View生命周期变换:" + event.name, view.javaClass.toString())
    }

    /*====== disposable manage methods ======*/
    fun addDisposable(d: Disposable) {
        LogUtils.eTag("添加请求", d.hashCode())
        mDisposableList.add(d)
    }

    /*====== base methods ======*/
    override fun hasLogin(): Boolean {
        return true
    }

    override fun logout() {

    }
}
