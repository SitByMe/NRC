package com.ta.netredcat.ui.modify_user_info

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.R
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.data.net.method.UserHttpMethods
import com.ta.netredcat.entity.UserInfo
import com.ta.netredcat.ui.BaseTitleActivity
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.entity.httpResult.EmptyBean
import com.tianao.module.net.http.ObserverOnNextListener
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_modify2.*

class Modify2Activity : BaseTitleActivity() {
    companion object {
        const val EXTRA_TYPE_ORDINAL = "extra_type_ordinal"
        const val EXTRA_USER_INFO = "extra_user_info"
    }

    /**
     * 修改类型
     */
    private var changeType = CHANGE_TYPE.NON
    private lateinit var userInfo: UserInfo

    override fun getTitleText(): String = getCurrentTitle(changeType)

    override fun getContentLayoutId(): Int = R.layout.activity_modify2

    override fun onCreate(savedInstanceState: Bundle?) {
        val d = intent.extras?.getInt(EXTRA_TYPE_ORDINAL) ?: CHANGE_TYPE.NON.ordinal
        for (item in CHANGE_TYPE.values()) {
            if (d == item.ordinal) {
                changeType = item
            }
        }
        super.onCreate(savedInstanceState)
        userInfo = intent.extras?.getParcelable(EXTRA_USER_INFO)
            ?: throw KotlinNullPointerException("userInfo can not be null")
    }

    override fun onResume() {
        super.onResume()
        btn_save.setOnClickListener {
            when (changeType) {
                CHANGE_TYPE.NICK_NAME -> et_input.text?.let {
                    userInfo.nickname = et_input.text.toString()
                }
                CHANGE_TYPE.INTRODUCTION -> et_input.text?.let {
                    userInfo.nickname = et_input.text.toString()
                }
                else -> {
                }
            }
            modifyInfo(userInfo)
        }
    }

    private fun modifyInfo(userInfo: UserInfo) {
        UserHttpMethods.modifyUserInfo(
            userInfo.face,
            userInfo.nickname,
            userInfo.introduction,
            Consumer { showLoadingView() },
            AppObserver(object : ObserverOnNextListener<EmptyBean>() {
                override fun onCompleted() {
                    super.onCompleted()
                    dismissLoadingView()
                }

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    dismissLoadingView()
                }

                override fun onNext(result: AppResult<EmptyBean>?) {
                    when (result?.status) {
                        200 -> {
                            ToastUtils.showShort(result.msg)
                            finishActivity(0x1234)
                        }
                        else -> ToastUtils.showShort(result?.msg)
                    }
                }
            })
        )
    }

    private fun getCurrentTitle(changeType: CHANGE_TYPE): String =
        when (changeType) {
            CHANGE_TYPE.NICK_NAME -> "昵称"
            CHANGE_TYPE.INTRODUCTION -> "签名"
            else -> "未知"
        }

    /**
     * 修改类型枚举
     */
    enum class CHANGE_TYPE {
        NON, NICK_NAME, INTRODUCTION
    }
}
