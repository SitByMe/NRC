package com.ta.netredcat.ui.register

import android.os.Bundle
import android.text.TextUtils
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleMVPActivity
import com.ta.netredcat.utils.NumberUtils
import kotlinx.android.synthetic.main.activity_register2.*
import kotlinx.android.synthetic.main.activity_register2.btn_send_verify_code

class Register2Activity : BaseTitleMVPActivity<Register2Presenter>(), Register2View {
    override fun setBtnVerifyCodeEnable(enable: Boolean) {
        btn_send_verify_code.isEnabled = enable
    }

    override fun setBtnVerifyCodeText(text: String) {
        btn_send_verify_code.text = text
    }

    override fun setBtnLoginEnable(enable: Boolean) {
        btn_register.isEnabled = enable
    }

    override fun getTitleText(): String = ""

    override fun getContentLayoutId(): Int = R.layout.activity_register2

    override fun initPresenter(): Register2Presenter? {
        return Register2Presenter(this, this)
    }

    companion object {
        const val EXTRA_INV_CODE: String = "extra_inv_code"
        const val EXTRA_PHONE: String = "extra_phone"
    }

    private lateinit var invCode: String
    private lateinit var phone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        invCode = intent.extras!!.getString(EXTRA_INV_CODE, "")
        phone = intent.extras!!.getString(EXTRA_PHONE, "")

    }

    override fun onResume() {
        super.onResume()
        iv_check_code.setOnClickListener { presenter!!.requestCheckCode() }

        btn_send_verify_code.setOnClickListener {
            if (NumberUtils.isPhone(phone)) {
                sendVerifyCode()
            } else {
                ToastUtils.showLong("请输入正确的手机号")
            }
        }

        btn_register.setOnClickListener {
            if (TextUtils.isEmpty(et_check_code.text.toString())) {
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(et_verify_code.text.toString())) {
                return@setOnClickListener
            }
            if (NumberUtils.isValidPassword(et_password.text.toString())) {
                //presenter!!.register()
                ToastUtils.showShort("注册")
            }
        }
    }

    /**
     * 发送验证码
     */
    private fun sendVerifyCode() {
        presenter!!.sendVerifyCode(phone, et_check_code.text.toString())
    }
}
