package com.ta.netredcat.ui.feed_back.my_feed

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

import com.ta.netredcat.R
import com.ta.netredcat.entity.MyFeedBackBean
import com.ta.netredcat.utils.ShowImageHelper
import com.ta.netredcat.view.recyclerview.BaseWithEmptyViewAdapter

class MyFeedBackAdapter(context: Context) : BaseWithEmptyViewAdapter<MyFeedBackBean>(context) {

    override fun onCreateEmptyHolder(
        mInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = mInflater.inflate(R.layout.layout_message_empty_list, parent, false)
        return EmptyViewHolder(view)
    }

    override fun onBindEmptyHolder(holder: RecyclerView.ViewHolder) {
        (holder as EmptyViewHolder).tvEmpty.text = "你宝贵的意见将是我们改进的源泉！"
    }

    override fun onCreateItemHolder(
        mInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = mInflater.inflate(R.layout.item_my_feedback, parent, false)
        return MyFeedBackViewHolder(view)
    }

    override fun onBindItemHolder(
        mDataList: List<MyFeedBackBean>,
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as MyFeedBackViewHolder).question.text = mDataList[position].type
        holder.answer.text = mDataList[position].content
        holder.time.text = String.format("反馈时间：%s", mDataList[position].time)
        holder.reply.text = mDataList[position].replys
        if (TextUtils.isEmpty(mDataList[position].image)) {
            holder.imgLayout.visibility = View.GONE
        } else {
            holder.imgLayout.visibility = View.VISIBLE
        }
        if (TextUtils.isEmpty(mDataList[position].replys)) {
            holder.replyLayout.visibility = View.GONE
        } else {
            holder.replyLayout.visibility = View.VISIBLE
        }
        ShowImageHelper.loadRect(mContext, mDataList[position].image, holder.img)
    }

    internal class MyFeedBackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var question: TextView = itemView.findViewById(R.id.question)
        var answer: TextView = itemView.findViewById(R.id.answer)
        var time: TextView = itemView.findViewById(R.id.time)
        var reply: TextView = itemView.findViewById(R.id.reply)
        var img: AppCompatImageView = itemView.findViewById(R.id.img)
        var imgLayout: LinearLayout = itemView.findViewById(R.id.imgLayout)
        var replyLayout: LinearLayout = itemView.findViewById(R.id.replyLayout)
    }

    internal class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivEmpty: AppCompatImageView = view.findViewById(R.id.iv_empty)
        var tvEmpty: AppCompatTextView = view.findViewById(R.id.tv_empty)
    }
}
