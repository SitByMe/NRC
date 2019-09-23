package com.ta.netredcat.entity

import com.google.gson.annotations.SerializedName

class ServiceBeanResult {
    /**
     * id : 1
     * customer_icon : /uploads/20190627/5cdc9f7f81ab8353324381e42da9f903.jpg
     * customer_name : QQ客服
     * customer_number : 1427370420
     */

    var id: Int = -1
    /**
     * 客服图标
     */
    @SerializedName("customer_icon")
    var customerIcon: String = null.toString()
    /**
     * 客服昵称
     */
    @SerializedName("customer_name")
    var customerName: String = null.toString()
    /**
     * 客服号码
     */
    @SerializedName("customer_number")
    var customerNumber: String = null.toString()
    /**
     * 1-QQ 2-微信
     */
    var type: Int = -1
}
