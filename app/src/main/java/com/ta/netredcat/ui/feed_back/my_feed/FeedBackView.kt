package com.ta.netredcat.ui.feed_back.my_feed

import com.ta.netredcat.entity.MyFeedBackBean
import com.ta.netredcat.ui.mvp.AppView

interface FeedBackView : AppView {
    fun setDate(list: List<MyFeedBackBean>)
}
//    void setFeed2Date(List<String> list);
//    void finishView();
