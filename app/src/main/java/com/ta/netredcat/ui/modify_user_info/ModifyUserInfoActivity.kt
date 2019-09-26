package com.ta.netredcat.ui.modify_user_info

import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleActivity
import com.ta.netredcat.utils.ShowImageHelper
import com.ta.netredcat.utils.helper.SpHelper
import kotlinx.android.synthetic.main.activity_modify_user_info.*

class ModifyUserInfoActivity : BaseTitleActivity() {
    private var userInfo = SpHelper.getUserInfo()

    override fun getTitleText(): String =
        resources.getString(R.string.activity_title_modify_user_info)

    override fun getContentLayoutId(): Int = R.layout.activity_modify_user_info

    override fun onStart() {
        super.onStart()

        ShowImageHelper.loadCircle(this, userInfo.face, iv_user_face)
        tv_phone.text = userInfo.phone
        tv_nick_name.text = userInfo.nickname
        tv_introduction.text = userInfo.introduction
        tv_introduction_length.text = "${userInfo.introduction.length}/50"
    }

    override fun onResume() {
        super.onResume()
        val bundle = Bundle()
        bundle.putInt(
            Modify2Activity.EXTRA_TYPE_ORDINAL,
            Modify2Activity.CHANGE_TYPE.NICK_NAME.ordinal
        )
        bundle.putParcelable(Modify2Activity.EXTRA_USER_INFO, userInfo)
        rl_nick_name.setOnClickListener {
            ActivityUtils.startActivityForResult(
                bundle,
                this,
                Modify2Activity::class.java,
                0x1234
            )
        }
    }
}

