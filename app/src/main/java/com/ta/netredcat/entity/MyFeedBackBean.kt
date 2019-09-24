package com.ta.netredcat.entity

data class MyFeedBackBean(
/*{
    "id": 1,
    "uid":1,
    "type": "app无法下单",//类型
    "content": "我下不了单",//反馈内容
    "image": "/img.png",//图片
    "replys": "谢谢你的反馈",//回复 未回复反 ""
    "time": "2019-08-13 15:11:59"
}*/

    val content: String,
    val id: Int,
    val image: String,
    val replys: String,
    val time: String,
    val type: String,
    val uid: Int
)