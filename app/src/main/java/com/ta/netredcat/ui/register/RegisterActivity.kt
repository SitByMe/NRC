package com.ta.netredcat.ui.register

import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleActivity
import com.ta.netredcat.utils.NumberUtils
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.et_user_name

class RegisterActivity : BaseTitleActivity() {
    override fun getContentLayoutId(): Int = R.layout.activity_register

    override fun getTitleText(): String = ""
    override fun onResume() {
        super.onResume()
        btn_next.setOnClickListener {
            if (NumberUtils.isPhone(et_user_name.text.toString())) {
                nextStep(et_inv_code.text.toString(), et_user_name.text.toString())
            } else {
                ToastUtils.showShort("请输入正确的手机号")
            }
        }
    }

    private fun nextStep(verifyCode: String?, phone: String) {
        val bundle = Bundle()
        bundle.putString(Register2Activity.EXTRA_INV_CODE, verifyCode)
        bundle.putString(Register2Activity.EXTRA_PHONE, phone)
        ActivityUtils.startActivity(bundle, Register2Activity::class.java)
    }
}
