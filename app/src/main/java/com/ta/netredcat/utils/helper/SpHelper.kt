package com.ta.netredcat.utils.helper

import com.ta.netredcat.BuildConfig
import com.ta.netredcat.constant.SpConstants
import com.ta.netredcat.entity.UserInfo
import com.ta.netredcat.entity.result.LoginResult
import com.ta.netredcat.utils.SpUtils
import com.tianao.module.net.entity.httpResult.AppResult

object SpHelper {
    val apiHost: String
        get() = SpUtils.getString(SpConstants.API_HOST, BuildConfig.API_HOST)

    /**
     * 保存用户信息
     * @param result 用户信息
     */
    fun initUserInfo(result: AppResult<LoginResult>) {
        SpUtils.putInt(SpConstants.UID, result.data.uid)
        SpUtils.putString(SpConstants.NICK_NAME, result.data.nickname)
        SpUtils.putString(SpConstants.PHONE, result.data.phone)
        SpUtils.putString(SpConstants.USER_TOKEN, result.data.logintoken)
        SpUtils.putString(SpConstants.HEAD_ICON, result.data.face)
        SpUtils.putString(SpConstants.AUTOGRAPH, result.data.autograph)
    }

    /**
     * 获取用户信息
     */
    fun getUserInfo(): UserInfo = UserInfo(
        SpUtils.getString(SpConstants.NICK_NAME),
        SpUtils.getString(SpConstants.PHONE),
        SpUtils.getString(SpConstants.INTRODUCTION, "添加你的个人简介"),
        SpUtils.getString(SpConstants.HEAD_ICON)
    )

    /**
     * 移除用户信息
     */
    fun removeUserInfo() {
        SpUtils.remove(SpConstants.UID)
        SpUtils.remove(SpConstants.NICK_NAME)
        SpUtils.remove(SpConstants.PHONE)
        SpUtils.remove(SpConstants.USER_TOKEN)
        SpUtils.remove(SpConstants.HEAD_ICON)
        SpUtils.remove(SpConstants.AUTOGRAPH)
    }
}
