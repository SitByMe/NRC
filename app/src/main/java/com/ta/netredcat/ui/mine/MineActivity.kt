package com.ta.netredcat.ui.mine

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.R
import com.ta.netredcat.entity.result.ServiceBeanResult
import com.ta.netredcat.entity.result.UserInfoResult
import com.ta.netredcat.settings.SettingsActivity
import com.ta.netredcat.ui.BaseTitleMVPActivity
import com.ta.netredcat.ui.modify_user_info.ModifyUserInfoActivity
import com.ta.netredcat.utils.ShowImageHelper
import kotlinx.android.synthetic.main.activity_basetitle.*
import kotlinx.android.synthetic.main.activity_mine.*

class MineActivity : BaseTitleMVPActivity<MinePresenter>(), MineView {
    override fun setServiceData(serviceBeanList: List<ServiceBeanResult>) {
        rv_customer_service.layoutManager = GridLayoutManager(this, 2)
        /* rv_customer_service.addItemDecoration(
             SimpleDividerItemDecoration(
                 this,
                 LineDrawable(R.color.bg_color_pink), 2
             )
         )*/
        val csvAdapter = CustomerServiceAdapter(this, serviceBeanList)
        rv_customer_service.adapter = csvAdapter
    }

    override fun setUserInfoData(userInfoData: UserInfoResult) {
        ShowImageHelper.loadCircle(applicationContext, userInfoData.face, iv_user_face)
        tv_user_name.text = userInfoData.nickName
        iv_is_vip.visibility = if (userInfoData.isVip()) View.VISIBLE else View.GONE
        tv_user_autograph.text = userInfoData.autograph
    }

    override fun getTitleText(): String = resources.getString(R.string.activity_title_mine)

    override fun getContentLayoutId(): Int = R.layout.activity_mine

    override fun initPresenter(): MinePresenter? {
        return MinePresenter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        title_bar.ivRight.setBackgroundResource(R.mipmap.ic_settings)
    }

    override fun onResume() {
        super.onResume()
        cl_head.setOnClickListener { ActivityUtils.startActivity(ModifyUserInfoActivity::class.java) }
        title_bar.setOnClickListener { ActivityUtils.startActivity(SettingsActivity::class.java) }
        cl_message_center.setOnClickListener { ToastUtils.showShort("消息中心") }
        cl_common_id.setOnClickListener { ToastUtils.showShort("常用ID") }
        cl_my_order.setOnClickListener { ToastUtils.showShort("我的订单") }
        cl_my_promotion.setOnClickListener { ToastUtils.showShort("我的推广") }

        presenter!!.requestUserInfo()
        presenter!!.requestCustomerServiceList()
    }
}
