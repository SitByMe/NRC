package com.ta.netredcat.view.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

import com.ta.netredcat.R

import java.util.ArrayList

abstract class BaseWithEmptyViewAdapter<B>(var mContext: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mDataList: MutableList<B>? = null
    private val mInflater: LayoutInflater

    private var mOnItemClickListener: OnItemClickListener<B>? = null

    private var onEmptyViewClickList: OnEmptyViewClickList? = null

    /**
     * 空数据时，显示空布局类型
     */
    private val EMPTY_VIEW = 1

    /**
     * 控制空布局的显隐 1：显示1个布局；0：不显示任何布局
     */
    private var mEmptyType = 0

    val dataCount: Int
        get() = mDataList!!.size

    val allData: List<B>?
        get() = mDataList

    interface OnItemClickListener<B> {
        fun onItemClick(view: View, item: B, position: Int)
    }

    interface OnEmptyViewClickList {
        fun onEmptyClick()
    }

    init {
        this.mDataList = ArrayList()
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (EMPTY_VIEW != viewType) {
            //返回空布局的viewHolder
            onCreateItemHolder(mInflater, parent, viewType)
        } else onCreateEmptyHolder(mInflater, parent, viewType)
    }

    open fun onCreateEmptyHolder(
        mInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = View(mContext)
        return DefaultEmptyHolder(view)
    }

    open fun onBindEmptyHolder(holder: RecyclerView.ViewHolder) {}

    abstract fun onCreateItemHolder(
        mInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder

    abstract fun onBindItemHolder(
        mDataList: List<B>,
        holder: RecyclerView.ViewHolder,
        position: Int
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        if (EMPTY_VIEW != itemViewType) {
            mDataList?.let { onBindItemHolder(it, holder, position) }
            initItemClickListener(holder, position)
        } else {
            onBindEmptyHolder(holder)
        }
    }

    private fun initItemClickListener(holder: RecyclerView.ViewHolder, position: Int) {
        if (useInitItemClick()) {
            //如果设置了回调，则设置点击事件
            holder.itemView.setOnClickListener {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener!!.onItemClick(
                        holder.itemView,
                        mDataList!![position],
                        position
                    )
                }
            }
        }
    }

    private fun useInitItemClick(): Boolean {
        return true
    }

    override fun getItemCount(): Int {
        return if (mDataList != null) mDataList!!.size + mEmptyType else mEmptyType
    }

    override fun getItemViewType(position: Int): Int {
        return if (mEmptyType == EMPTY_VIEW) {
            //空布局的类型
            EMPTY_VIEW
        } else super.getItemViewType(position)
    }

    fun getItem(position: Int): B {
        return mDataList!![position]
    }

    /**
     * 显示添加的数据
     * 如果数据为空，则显示空布局
     */
    fun addData(data: List<B>?) {
        if (data != null && data.isNotEmpty()) {
            if (mEmptyType == 1) {
                mEmptyType = 0
                notifyItemRemoved(0)
            }
            mDataList!!.addAll(data)
            if (mDataList!!.size == 0) {
                showEmptyView()
            } else {
                notifyDataSetChanged()
            }
        } else if (mDataList!!.size == 0) {
            if (mEmptyType != EMPTY_VIEW) {
                mEmptyType = EMPTY_VIEW
                notifyItemInserted(0)
            }
        }
    }

    /**
     * 显示空布局
     */
    private fun showEmptyView() {
        if (mDataList!!.isNotEmpty()) {
            mDataList!!.clear()
            notifyDataSetChanged()
        }
        if (mEmptyType != EMPTY_VIEW) {
            //当前布局不是空布局，则刷新显示空布局
            mEmptyType = EMPTY_VIEW
            notifyItemInserted(0)
        }
    }

    fun clearData() {
        if (mDataList!!.isNotEmpty()) {
            mDataList!!.clear()
        }
    }

    fun setNormalDecoration(rv: RecyclerView, context: Context) {
        val divider = DividerItemDecoration(context, LinearLayout.VERTICAL)
        divider.setDrawable(context.resources.getDrawable(R.drawable.item_line))
        rv.addItemDecoration(divider)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener<B>) {
        this.mOnItemClickListener = onItemClickListener
    }

    fun setOnEmptyViewClickList(onEmptyViewClickList: OnEmptyViewClickList) {
        this.onEmptyViewClickList = onEmptyViewClickList
    }

    internal class DefaultEmptyHolder(view: View) : RecyclerView.ViewHolder(view)
}
