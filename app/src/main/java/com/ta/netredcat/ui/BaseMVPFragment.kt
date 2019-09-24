package com.ta.netredcat.ui

import android.os.Bundle
import android.view.View
import com.ta.netredcat.ui.mvp.AppPresenter
import com.ta.netredcat.ui.mvp.AppView

abstract class BaseMVPFragment<P : AppPresenter<*, *>> : BaseFragment(), AppView {
    var presenter: P? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = initPresenter()
        if (presenter != null) {
            // 初始化MVP的时候，添加生命周期监听
            lifecycle.addObserver(presenter!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
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
        activity?.finish()
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

    override fun setContentLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}