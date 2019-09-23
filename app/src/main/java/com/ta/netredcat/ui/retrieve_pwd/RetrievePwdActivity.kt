package com.ta.netredcat.ui.retrieve_pwd

import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleMVPActivity
import com.ta.netredcat.utils.NumberUtils
import kotlinx.android.synthetic.main.activity_retrieve_pwd.*
import kotlinx.android.synthetic.main.activity_retrieve_pwd.et_user_name

class RetrievePwdActivity : BaseTitleMVPActivity<RetrievePwdPresenter>(), RetrievePwdView {
    override fun setBtnLoginEnable(enable: Boolean) {
        btn_next_step.isEnabled = enable
    }

    override fun getTitleText(): String = ""
    override fun getContentLayoutId(): Int = R.layout.activity_retrieve_pwd

    override fun initPresenter(): RetrievePwdPresenter? {
        return RetrievePwdPresenter(this, this)
    }

    override fun onResume() {
        super.onResume()
        iv_check_code.setOnClickListener { presenter!!.requestCheckCode() }
        btn_next_step.setOnClickListener {
            if (NumberUtils.isPhone(et_user_name.text.toString())) {
                nextStep(et_check_code.text.toString(), et_user_name.text.toString())
            } else {
                ToastUtils.showShort("请输入正确的手机号")
            }
        }
    }

    private fun nextStep(verifyCode: String?, phone: String) {
        val bundle = Bundle()
        bundle.putString(RetrievePwd2Activity.EXTRA_CHECK_CODE, verifyCode)
        bundle.putString(RetrievePwd2Activity.EXTRA_PHONE, phone)
        ActivityUtils.startActivity(bundle, RetrievePwd2Activity::class.java)
    }
}
