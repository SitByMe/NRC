package com.ta.netredcat.settings

import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.App
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleActivity
import com.ta.netredcat.ui.feed_back.FeedBackActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseTitleActivity() {
    override fun getTitleText(): String = resources.getString(R.string.activity_title_settings)

    override fun getContentLayoutId(): Int = R.layout.activity_settings

    override fun onResume() {
        super.onResume()
        cl_feed_back.setOnClickListener { ActivityUtils.startActivity(FeedBackActivity::class.java) }
        cl_change_pwd.setOnClickListener { ToastUtils.showShort("修改密码") }
        cl_app_version.setOnClickListener { ToastUtils.showShort("版本信息") }
        cl_clear_cache.setOnClickListener { ToastUtils.showShort("清除缓存") }
        btn_logout.setOnClickListener {
            App.mApp.logOut()
            finish()
        }
    }
}
