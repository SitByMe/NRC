package com.ta.netredcat.ui.login

import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleMVPActivity
import com.ta.netredcat.ui.register.RegisterActivity
import com.ta.netredcat.ui.retrieve_pwd.RetrievePwdActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseTitleMVPActivity<LoginPresenter>(), LoginView {

    override fun getTitleText(): String = ""

    override fun getContentLayoutId(): Int = R.layout.activity_login

    override fun onResume() {
        super.onResume()
        tv_to_register.setOnClickListener { ActivityUtils.startActivity(RegisterActivity::class.java) }
        tv_to_change_pwd.setOnClickListener { ActivityUtils.startActivity(RetrievePwdActivity::class.java) }

        btn_login.setOnClickListener {
            val userName = phone_et.getText()
            val verifyCode = pwd_et.getText()

            when {
                userName.isEmpty() -> ToastUtils.showShort("电话号码不能为空")
                verifyCode.isEmpty() -> ToastUtils.showShort("验证码不能为空")
                else -> login(userName, verifyCode)
            }
        }
    }

    override fun initPresenter(): LoginPresenter? {
        return LoginPresenter(this, this)
    }

    /**
     * 登录
     */
    private fun login(userName: String, verifyCode: String) {
        ToastUtils.showShort(userName.plus("-").plus(verifyCode))
        presenter!!.login(userName, verifyCode, "")
    }
}
