package com.ta.netredcat.ui.modify_user_info

import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleActivity
import com.ta.netredcat.utils.ShowImageHelper
import com.ta.netredcat.utils.helper.SpHelper
import kotlinx.android.synthetic.main.activity_modify_user_info.*

class ModifyUserInfoActivity : BaseTitleActivity() {
    override fun getTitleText(): String =
        resources.getString(R.string.activity_title_modify_user_info)

    override fun getContentLayoutId(): Int = R.layout.activity_modify_user_info

    override fun onStart() {
        super.onStart()
        val userInfo = SpHelper.getUserInfo()
        ShowImageHelper.loadCircle(this, userInfo.face, iv_user_face)
        tv_phone.text = userInfo.phone
        tv_nick_name.text = userInfo.nickname
        tv_introduction.text = userInfo.introduction
        tv_introduction_length.text = "${userInfo.introduction.length}/50"
    }
}

