package com.ta.netredcat.ui

import android.os.Bundle
import com.ta.netredcat.ui.mvp.AppPresenter
import com.ta.netredcat.ui.mvp.AppView
import io.reactivex.disposables.Disposable

abstract class BaseMVPActivity<P : AppPresenter<*, *>> : BaseActivity(), AppView {

    var presenter: P? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = initPresenter()
        if (presenter != null) {
            // 初始化MVP的时候，添加生命周期监听
            lifecycle.addObserver(presenter!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null) {
            presenter!!.detachView()
            presenter = null
        }
    }

    open fun initPresenter(): P? {
        return null
    }

    /* implement from AppView */
    override fun showLoadingView() {
    }

    override fun showLoadingView(lightTextMode: Boolean) {
    }

    override fun dismissLoadingView() {
    }

    override fun finishRefresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finishActivity() {
        finish()
    }

    override fun showToast(toastStr: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRootUIVisibility(visibility: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNetErrorLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeNetErrorLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*====== disposable manage methods ======*/
    private var mDisposableList: MutableList<Disposable> = mutableListOf()

    public fun addDisposable(d: Disposable) {
        if (presenter != null) {
            presenter!!.addDisposable(d)
        } else {
            mDisposableList.add(d)
        }
    }
}