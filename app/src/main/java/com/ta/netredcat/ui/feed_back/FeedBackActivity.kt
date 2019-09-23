package com.ta.netredcat.ui.feed_back

import android.os.Bundle
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.ui.feed_back.feed.FeedBackFragment
import com.ta.netredcat.ui.feed_back.my_feed.MyFeedFragment
import kotlinx.android.synthetic.main.activity_feed_back.*

class FeedBackActivity : BaseTitleActivity() {
    override fun getTitleText(): String = resources.getString(R.string.activity_title_feed_back)

    override fun getContentLayoutId(): Int = R.layout.activity_feed_back

    private val fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentList.add(FeedBackFragment())
        fragmentList.add(MyFeedFragment())
        val fragmentAdapter = FeedBackPageAdapter(supportFragmentManager, fragmentList)
        vp_feed_back.adapter = fragmentAdapter
        vp_feed_back.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        ToastUtils.showShort("意见反馈")
                    }
                    1 -> {
                        ToastUtils.showShort("我的反馈")
                    }
                    else -> {
                    }
                }
            }
        })
    }
}
