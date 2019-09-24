package com.ta.netredcat.ui.feed_back.my_feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ta.netredcat.R
import com.ta.netredcat.entity.MyFeedBackBean
import com.ta.netredcat.ui.BaseMVPFragment
import kotlinx.android.synthetic.main.fragment_layout_my_feed.*

class MyFeedFragment : BaseMVPFragment<FeedPresenter>(), FeedBackView {
    override fun setDate(list: List<MyFeedBackBean>) {
        adapter?.clearData()
        adapter?.addData(list)
        rv_feed_back.scrollToPosition(0)
    }

    private var adapter: MyFeedBackAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_layout_my_feed, container, false)
    }

    override fun initPresenter(): FeedPresenter? {
        return FeedPresenter(context!!, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_feed_back.layoutManager = LinearLayoutManager(activity)
        if (context != null && adapter == null) {
            adapter = MyFeedBackAdapter(context!!)
            rv_feed_back.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        presenter?.requestMyFeedBack()
    }
}