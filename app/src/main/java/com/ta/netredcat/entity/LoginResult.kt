package com.ta.netredcat.entity

class LoginResult {
    /**
     * uid : 6
     * nickname : nick
     * phone : 18302824291
     * logintoken : 9fe435fe738e6b35e377434688fa5bef5dcf3c65
     * face : /uploads/default.png
     * autograph : 一个神秘的网红猫用户
     */

    /**
     * 用户uid
     */
    var uid: Int = 0
    /**
     * 用户昵称
     */
    var nickname: String? = null
    /**
     * 用户电话
     */
    var phone: String? = null
    /**
     *  用户登录凭证
     */
    var logintoken: String? = null
    /**
     * 用户头像
     */
    var face: String? = null
    /**
     * 签名
     */
    var autograph: String? = null
}
