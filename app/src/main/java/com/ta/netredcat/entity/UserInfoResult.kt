package com.ta.netredcat.entity

import com.google.gson.annotations.SerializedName

class UserInfoResult {
    /**
     * uid : 6
     * status : 1
     * nickname : nick
     * face : /uploads/default.png
     * autograph : 一个神秘的网红猫用户
     * gold_coin : 710
     * vip_grade : 0
     * invite_code : AQZMLJHT
     * titles : 扫描推广网红猫APP
     * spread_url : http://maomiapp.znysc.top?inviteCode=AQZMLJHT
     * spread_rule : http://www.znysc.top/explain/spread.html
     * vip_rule : http://www.znysc.top/explain/vip.html
     */

    /**
     * 用户id
     */
    var uid: Int = 0
    /**
     * 用户状态
     */
    var status: String? = null
    /**
     * 用户昵称
     */
    @SerializedName("nickname")
    var nickName: String? = null
    /**
     * 用户头像
     */
    var face: String = null.toString()
    /**
     * 签名
     */
    var autograph: String? = null
    /**
     * 猫币个数
     */
    @SerializedName("gold_coin")
    var goldCoin: Int = 0
    /**
     * 是否vip 1-是 0-否
     */
    @SerializedName("vip_grade")
    private var vipGrade: Int = 0
    /**
     * 邀请码
     */
    @SerializedName("invite_code")
    var inviteCode: String? = null
    var titles: String? = null
    /**
     * 分销地址
     */
    @SerializedName("spread_url")
    var spreadUrl: String? = null
    /**
     * 推广规则
     */
    @SerializedName("spread_rule")
    var spreadRule: String? = null
    /**
     * vip规则
     */
    @SerializedName("vip_rule")
    var vipRule: String? = null

    fun isVip() = vipGrade == 1
}
