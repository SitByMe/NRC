package com.ta.netredcat.ui.retrieve_pwd

import android.os.Bundle
import android.text.TextUtils
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleMVPActivity
import com.ta.netredcat.utils.NumberUtils
import kotlinx.android.synthetic.main.activity_retrieve_pwd2.*
import kotlinx.android.synthetic.main.activity_retrieve_pwd2.btn_send_verify_code
import kotlinx.android.synthetic.main.activity_retrieve_pwd2.et_password
import kotlinx.android.synthetic.main.activity_retrieve_pwd2.et_verify_code

class RetrievePwd2Activity : BaseTitleMVPActivity<RetrievePwd2Presenter>(), RetrievePwd2View {
    override fun setBtnVerifyCodeEnable(enable: Boolean) {
        btn_send_verify_code.isEnabled = enable
    }

    override fun setBtnVerifyCodeText(text: String) {
        btn_send_verify_code.text = text
    }

    override fun setBtnLoginEnable(enable: Boolean) {
        btn_confirm.isEnabled = enable
    }

    override fun getTitleText(): String = ""

    override fun getContentLayoutId(): Int = R.layout.activity_retrieve_pwd2
    override fun initPresenter(): RetrievePwd2Presenter? {
        return RetrievePwd2Presenter(this, this)
    }

    companion object {
        const val EXTRA_CHECK_CODE: String = "extra_check_code"
        const val EXTRA_PHONE: String = "extra_phone"
    }

    private lateinit var checkCode: String
    private lateinit var phone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkCode = intent.extras!!.getString(EXTRA_CHECK_CODE, "")
        phone = intent.extras!!.getString(EXTRA_PHONE, "")
    }

    override fun onResume() {
        super.onResume()
        btn_send_verify_code.setOnClickListener { sendVerifyCode() }
        btn_confirm.setOnClickListener {
            if (TextUtils.isEmpty(et_verify_code.text.toString())) {
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(et_password.text.toString())) {
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
        presenter!!.sendVerifyCode(phone, "safd")
    }
}
