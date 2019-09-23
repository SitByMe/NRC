package com.ta.netredcat.ui.recharge_must_see

import android.os.Bundle
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleActivity
import kotlinx.android.synthetic.main.activity_recharge_must_see.*

class RechargeMustSeeActivity : BaseTitleActivity() {
    companion object {
        const val EXTRA_CONTENT = "extra_content"
    }

    override fun getTitleText(): String = "充值必看"

    override fun getContentLayoutId(): Int = R.layout.activity_recharge_must_see

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = intent.extras?.getString(EXTRA_CONTENT, "没有内容")
        web_view.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
    }
}
