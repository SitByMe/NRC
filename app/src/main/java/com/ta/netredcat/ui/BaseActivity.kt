package com.ta.netredcat.ui

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.blankj.utilcode.util.AdaptScreenUtils
import com.ta.netredcat.ui.mvp.AppPresenter
import com.ta.netredcat.ui.mvp.AppView
import io.reactivex.disposables.Disposable
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setContentLayout())
    }

    fun closeAdapt(): Boolean {
        return false
    }

    override fun getResources(): Resources {
        if (closeAdapt()) {
            return super.getResources()
        }
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080)
    }

    protected abstract fun setContentLayout(): Int
}