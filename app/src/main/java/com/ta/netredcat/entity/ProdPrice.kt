package com.ta.netredcat.entity

import com.google.gson.annotations.SerializedName

class ProdPrice {
    /**
     * id : 1
     * price : 129.00
     * name : 法国梦特娇（MONTAGUT）黑丽雅金夹钢笔0.5mm
     * category_id : 25
     * price_img : /uploads/20190711/f8febfdb150d7a3de43596d2ced008aa.png
     * desc_img : /uploads/20190712/0e9467eb7ff5c08a01784ea8155bf903.png
     */

    var id: Int = -1
    var price: String? = null
    var name: String? = null
    @SerializedName("category_id")
    var categoryId: Int = -1
    @SerializedName("price_img")
    var priceImg: String? = null
    @SerializedName("desc_img")
    var descImg: String? = null
}
