package com.ta.netredcat.ui

import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.ta.netredcat.R
import com.ta.netredcat.ui.main.MainActivity
import com.ta.netredcat.utils.helper.CountDownHelper
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_guide.*

class GuideActivity : BaseActivity() {
    override fun setContentLayout(): Int = R.layout.activity_guide

    private lateinit var dis: Disposable
    override fun onResume() {
        super.onResume()
        iv_logo.setOnClickListener { println("点击了logo") }
        dis = CountDownHelper.startCountDown(Consumer {
            if (it != 3L) {
                tv_count.text = "计时:$it"
                println("计时：$it")
            } else {
                if (!dis.isDisposed) {
                    dis.dispose()
                }
                ActivityUtils.startActivity(Intent(this@GuideActivity, MainActivity::class.java))
                finish()
            }
        })
    }
}
