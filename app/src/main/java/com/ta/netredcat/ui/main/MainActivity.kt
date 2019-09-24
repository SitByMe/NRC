package com.ta.netredcat.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.App
import com.ta.netredcat.R
import com.ta.netredcat.entity.result.HomeDataResult
import com.ta.netredcat.ui.BaseMVPActivity
import com.ta.netredcat.ui.mine.MineActivity
import com.ta.netredcat.ui.main.adapter.MainContentAdapter
import com.ta.netredcat.interface_enum.WEB_PAGE_TYPE
import com.ta.netredcat.utils.ShowImageHelper
import com.ta.netredcat.utils.helper.StartActivityUtils
import com.zhouwei.mzbanner.MZBannerView
import com.zhouwei.mzbanner.holder.MZViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVPActivity<MainPresener>(), MainView {

    private lateinit var adBanner: MZBannerView<HomeDataResult.RollLogBean>

    override fun setFlipperData(rollLog: List<HomeDataResult.RollLogBean>?) {
        if (rollLog.isNullOrEmpty()) {
            adBanner.visibility = View.GONE
        } else {
            adBanner.visibility = View.VISIBLE
            adBanner.setIndicatorVisible(false)
            adBanner.duration = 5000
            adBanner.setPages(rollLog) { AdBannerItemViewHolder() }
            adBanner.start()
        }
    }

    override fun setContentLayout(): Int = R.layout.activity_main

    override fun initPresenter(): MainPresener? {
        return MainPresener(this, this)
    }

    private lateinit var banner: MZBannerView<HomeDataResult.BannerBean>

    override fun setBannerData(bannerData: List<HomeDataResult.BannerBean>?) {
        if (bannerData.isNullOrEmpty()) {
            banner.visibility = View.GONE
        } else {
            banner.visibility = View.VISIBLE
            banner.setBannerPageClickListener { _, i ->
                when (bannerData[i].hrefType) {
                    1 -> StartActivityUtils.startToWeb(
                        this,
                        bannerData[i].name,
                        bannerData[i].url,
                        WEB_PAGE_TYPE.TYPE_INNER
                    )
                    2 -> {
                        StartActivityUtils.startToWeb(
                            this,
                            bannerData[i].name,
                            bannerData[i].url,
                            WEB_PAGE_TYPE.TYPE_OUTER
                        )
                    }
                    else -> ToastUtils.showShort(
                        String.format(
                            "未知类型 type = %&d",
                            bannerData[i].hrefType
                        )
                    )
                }
            }
        }
        banner.setPages(bannerData) { BannerItemViewHolder() }
        banner.start()
    }

    inner class AdBannerItemViewHolder : MZViewHolder<HomeDataResult.RollLogBean> {
        private lateinit var tvMsg: AppCompatTextView
        private lateinit var ivIsVip: AppCompatImageView
        private lateinit var tvName: AppCompatTextView
        private lateinit var tvRollType: AppCompatTextView

        override fun createView(context: Context): View {
            val view: View =
                LayoutInflater.from(mActivity).inflate(R.layout.item_layout_roll_log_item, null)
            tvMsg = view.findViewById(R.id.tv_msg)
            ivIsVip = view.findViewById(R.id.iv_is_vip)
            tvName = view.findViewById(R.id.tv_name)
            tvRollType = view.findViewById(R.id.tv_roll_type)
            return view
        }

        override fun onBind(context: Context, position: Int, data: HomeDataResult.RollLogBean) {
            tvMsg.text = data.categoryName
            tvName.text = data.nickname
            ivIsVip.visibility = if (!data.isVip()) View.VISIBLE else View.GONE
            tvRollType.text = data.type
        }
    }

    inner class BannerItemViewHolder : MZViewHolder<HomeDataResult.BannerBean> {
        private lateinit var mImageView: AppCompatImageView

        override fun createView(context: Context): View {
            val view = LayoutInflater.from(context).inflate(R.layout.banner_item, null)
            mImageView = view.findViewById(R.id.iv_banner_image)
            return view
        }

        override fun onBind(context: Context, position: Int, data: HomeDataResult.BannerBean) {
            ShowImageHelper.loadHalfCircle(context, data.image, mImageView)
        }
    }

    override fun setMainData(dataList: List<HomeDataResult.CategoryBean>) {
        homeDataList.addAll(dataList)
        homeDataAdapter.notifyDataSetChanged()
    }

    private var homeDataList: MutableList<HomeDataResult.CategoryBean> = mutableListOf()
    private lateinit var homeDataAdapter: MainContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adBanner = findViewById(R.id.ad_view)
        banner = findViewById(R.id.banner)
        initView()
        presenter!!.requestHomeData()
    }

    private val mActivity: MainActivity = this

    private fun initView() {
        rv_content.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        homeDataAdapter = MainContentAdapter(this, homeDataList)
        rv_content.adapter = homeDataAdapter
    }

    override fun onResume() {
        super.onResume()
        iv_user_face.setOnClickListener {
            if (App.mApp.hasLogin(true)) {
                ActivityUtils.startActivity(
                    Intent(
                        this,
                        MineActivity::class.java
                    )
                )
            }
        }
    }
}
