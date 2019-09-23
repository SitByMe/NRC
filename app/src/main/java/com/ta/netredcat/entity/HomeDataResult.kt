package com.ta.netredcat.entity

import com.google.gson.annotations.SerializedName

class HomeDataResult {
    /**
     * category_type : 0
     * notify_num : 0
     * banner : [{"name":"第一个banner","image":"/uploads/20190829/50d5c462fe196efcb4a56d81852149e5.jpg","href_type":1,"url":"http://www.baidu.com"}]
     * category : [{"id":4,"category_name":"快手","category_img":"","category_sort":"1","category_parent":0,"next_cate":[{"id":3,"category_name":"点赞","category_img":"","category_sort":"14","next_cate":[],"cate_price":{"price":"200.00","name":"点赞","times":1500}},{"id":56,"category_name":"套餐","category_img":"/uploads/20190718/67fb2870bb861cdeaa857a8cf3f3f9ee.png","category_sort":"0","category_parent":10,"next_cate":[],"cate_price":{"data":[{"id":2,"category_id":56,"name":"粉丝+转发","price":"998.00","meal_content":[{"meal_id":2,"category_id":14,"times":1500,"category_name":"转发"},{"meal_id":2,"category_id":11,"times":2500,"category_name":"粉丝"}]},{"id":3,"category_id":56,"name":"双击+点赞","price":"698.00","meal_content":[{"meal_id":3,"category_id":12,"times":1000,"category_name":"双击"},{"meal_id":3,"category_id":15,"times":1200,"category_name":"点赞"}]}]},"parent_name":"抖音","is_meal":1}],"cate_price":[]},{"id":1,"category_name":"抖音","category_img":"","category_sort":"0","category_parent":0,"next_cate":[{"id":2,"category_name":"评论","category_img":"","category_sort":"12","next_cate":[],"cate_price":{"price":"100.00","name":"评论","times":1000}}],"cate_price":[]}]
     * roll_log : [{"id":1,"user_id":1,"category_id":2,"category_price_id":1,"created_at":1561536706,"created_date":"1小时前,nickname用户购买了抖音评论!","face":"/uploads/20190710/5d5c0fbf9f43742110641ac81008e977.png"}]
     * app_config : {"app_name":"网红猫APP","must_see":"充值必看","alipay_pay":"1","wechat_pay":"1","alipay_config":"2","wechat_config":"1","alipay_pay_address":"https://ds.alipay.com/?from=mobilecodec&scheme=alipays%3A%2F%2Fplatformapi%2Fstartapp%3FsaId%3D10000007%26clientVersion%3D3.7.0.0718%26qrcode%3Dhttps%253A%252F%252Fqr.alipay.com%252Ftsx09788ozb2oekhv8mvmac%253F_s%253Dweb-other","wechat_pay_address":"http://www.znysc.top"}
     */

    /**
     * 分类类型  0-抖音/快手 1-商品
     */
    @SerializedName("category_type")
    var categoryType: Int = 0
    /**
     * 未读消息数量
     */
    @SerializedName("notify_num")
    var notifyNum: Int = 0

    /**
     * 首页banner
     */
    var banner: List<BannerBean>? = null
    /**
     * 首页分类数据
     */
    var category: List<CategoryBean>? = null
    /**
     * 首页滚动信息
     */
    @SerializedName("roll_log")
    var rollLog: List<RollLogBean>? = null
    @SerializedName("app_config")
    var appConfig: AppConfigBean? = null

    class AppConfigBean {
        /**
         * app_name : 网红猫APP
         * must_see : 充值必看
         * alipay_pay : 1
         * wechat_pay : 1
         * alipay_config : 2
         * wechat_config : 1
         * alipay_pay_address : https://ds.alipay.com/?from=mobilecodec&scheme=alipays%3A%2F%2Fplatformapi%2Fstartapp%3FsaId%3D10000007%26clientVersion%3D3.7.0.0718%26qrcode%3Dhttps%253A%252F%252Fqr.alipay.com%252Ftsx09788ozb2oekhv8mvmac%253F_s%253Dweb-other
         * wechat_pay_address : http://www.znysc.top
         */

        /**
         * 首页标题
         */
        @SerializedName("app_name")
        var appName: String? = null
        /**
         * 充值必看
         */
        @SerializedName("must_see")
        var mustSee: String? = null
        /**
         * 是否开启支付宝支付  1开启  2关闭
         */
        @SerializedName("alipay_pay")
        var alipayPay: String? = null
        /**
         * 是否开启微信支付  1开启 2关闭
         */
        @SerializedName("wechat_pay")
        var wechatPay: String? = null
        /**
         * 开启支付宝支付方式  1-APP支付（走商户中心） 2-扫码支付
         */
        @SerializedName("alipay_config")
        var alipayConfig: String? = null
        /**
         * 开启微信支付方式  1-APP支付（走商户中心） 2-扫码支付
         */
        @SerializedName("wechat_config")
        var wechatConfig: String? = null
        /**
         * 支付宝扫码支付地址
         */
        @SerializedName("alipay_pay_address")
        var alipayPayAddress: String? = null
        /**
         * 微信扫码支付地址
         */
        @SerializedName("wechat_pay_address")
        var wechatPayAddress: String? = null
    }

    class BannerBean {
        /**
         * name : 第一个banner
         * image : /uploads/20190829/50d5c462fe196efcb4a56d81852149e5.jpg
         * href_type : 1
         * url : http://www.baidu.com
         */

        var name: String? = null
        var image: String = ""
        /**
         * 跳转类型 1-内部 2-外部
         */
        @SerializedName("href_type")
        var hrefType: Int = 0
        var url: String = ""
    }

    class CategoryBean {
        /**
         * id : 4
         * is_new : 0
         * category_name : 快手
         * category_img :
         * category_sort : 1
         * category_parent : 0
         * next_cate : [{"id":3,"category_name":"点赞","category_img":"","category_sort":"14","next_cate":[],"cate_price":{"price":"200.00","name":"点赞","times":1500}},{"id":56,"category_name":"套餐","category_img":"/uploads/20190718/67fb2870bb861cdeaa857a8cf3f3f9ee.png","category_sort":"0","category_parent":10,"next_cate":[],"cate_price":{"data":[{"id":2,"category_id":56,"name":"粉丝+转发","price":"998.00","meal_content":[{"meal_id":2,"category_id":14,"times":1500,"category_name":"转发"},{"meal_id":2,"category_id":11,"times":2500,"category_name":"粉丝"}]},{"id":3,"category_id":56,"name":"双击+点赞","price":"698.00","meal_content":[{"meal_id":3,"category_id":12,"times":1000,"category_name":"双击"},{"meal_id":3,"category_id":15,"times":1200,"category_name":"点赞"}]}]},"parent_name":"抖音","is_meal":1}]
         * cate_price : []
         */

        /**
         * 分类id
         */
        var id: Int = 0
        /**
         * 是否是新产品 1-是 0-否
         */
        @SerializedName("is_new")
        private var isNew: Int = 0
        /**
         * 一级分类名称
         */
        @SerializedName("category_name")
        var categoryName: String? = null
        /**
         * 分类图标
         */
        @SerializedName("category_img")
        var categoryImg: String = ""
        /**
         * 分类排序
         */
        @SerializedName("category_sort")
        var categorySort: String? = null
        /**
         * 上级分类id
         */
        @SerializedName("category_parent")
        var categoryParent: Int = 0
        /**
         * 二级分类
         */
        @SerializedName("next_cate")
        var nextCate: List<NextCateBean>? = null
        @SerializedName("cate_price")
        var catePrice: List<*>? = null

        fun isNew(): Boolean = isNew == 1

        class NextCateBean {
            /**
             * id : 3
             * category_name : 点赞
             * category_img :
             * category_sort : 14
             * next_cate : []
             * cate_price : {"price":"200.00","name":"点赞","times":1500}
             * category_parent : 10
             * parent_name : 抖音
             * is_meal : 1
             */

            var id: Int = 0
            /**
             * // 二级分类名称
             */
            @SerializedName("category_name")
            var categoryName: String? = null
            /**
             * 是否是新产品 1-是 0-否
             */
            @SerializedName("is_new")
            private var isNew: Int = 0
            /**
             * 二级分类图标
             */
            @SerializedName("category_img")
            var categoryImg: String = ""
            @SerializedName("category_sort")
            var categorySort: String? = null
            /**
             * 详细信息
             */
            @SerializedName("cate_price")
            var catePrice: CatePriceBean? = null
            @SerializedName("category_parent")
            var categoryParent: Int = 0
            @SerializedName("parent_name")
            var parentName: String? = null
            @SerializedName("is_meal")
            var isMeal: Int = 0
            @SerializedName("next_cate")
            var nextCate: List<*>? = null

            fun isNew(): Boolean = isNew == 1

            class CatePriceBean {
                /**
                 * price : 200.00
                 * name : 点赞
                 * times : 1500
                 */
                /**
                 * 对应的价格
                 */
                var price: Double = 0.0
                /**
                 * 标注
                 */
                var name: String? = null
                /**
                 * 对应的次数/个数
                 */
                var times: Int = 0
                /**
                 * 套餐列表数据
                 */
                var data: List<DataBean>? = null

                class DataBean {
                    /**
                     * id : 2
                     * category_id : 56
                     * name : 粉丝+转发
                     * price : 998.00
                     * meal_content : [{"meal_id":2,"category_id":14,"times":1500,"category_name":"转发"},{"meal_id":2,"category_id":11,"times":2500,"category_name":"粉丝"}]
                     */

                    /**
                     * 套餐id
                     */
                    var id: Int = 0
                    /**
                     * 套餐所属分类 （抖音）
                     */
                    @SerializedName("category_id")
                    var categoryId: Int = 0
                    /**
                     * 套餐名称
                     */
                    var name: String? = null
                    /**
                     * 套餐价格
                     */
                    var price: Double = 0.0
                    /**
                     *  套餐具体包含内容
                     */
                    @SerializedName("meal_content")
                    var mealContent: List<MealContentBean>? = null

                    class MealContentBean {
                        /**
                         * meal_id : 2
                         * category_id : 14
                         * times : 1500
                         * category_name : 转发
                         */

                        /**
                         *  套餐具体数据id
                         */
                        @SerializedName("meal_id")
                        var mealId: Int = 0
                        /**
                         * 套餐数据所属分类id
                         */
                        @SerializedName("category_id")
                        var categoryId: Int = 0
                        /**
                         * 对应次数
                         */
                        var times: Int = 0
                        /**
                         * 套餐数据分类名称
                         */
                        @SerializedName("category_name")
                        var categoryName: String? = null
                    }
                }
            }
        }
    }

    class RollLogBean {
        /**
         * nickname : 147****4019
         * vip_grade : 0
         * category_name : 抖音100个低价真人DY粉丝
         * type : 用户购买
         */

        var nickname: String? = null
        /**
         * 是否vip 大于1 是 0-不是
         */
        @SerializedName("vip_grade")
        private var vipGrade: Int = 1
        /**
         * //购买产品
         */
        @SerializedName("category_name")
        var categoryName: String? = null
        var type: String? = null

        fun isVip(): Boolean = vipGrade == 1
    }
}
