package com.ta.netredcat.view.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ta.netredcat.R
import com.ta.netredcat.utils.DensityUtil

class SimpleDividerItemDecoration
/**
 * @param context         上下文
 * @param divider         分割线Drawable
 * @param dividerHeightDp 分割线高度
 */
    (context: Context, divider: Drawable?, dividerHeightDp: Int) : RecyclerView.ItemDecoration() {
    private var mDivider: Drawable? = null     //分割线Drawable
    private val mDividerHeight: Int  //分割线高度

    /**
     * 使用line_divider中定义好的颜色
     *
     * @param context         上下文
     * @param dividerHeightDp 分割线高度
     */
    constructor(context: Context, dividerHeightDp: Int) : this(context, null, dividerHeightDp)

    init {
        mDivider = divider ?: ContextCompat.getDrawable(context, R.color.transparent)
        mDividerHeight = DensityUtil.dp2px(dividerHeightDp.toFloat())
    }

    //获取分割线尺寸
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.layoutManager is GridLayoutManager) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.bottom = mDividerHeight
            outRect.left = mDividerHeight / 2
            outRect.right = mDividerHeight / 2
            //由于每行都只有3个，所以第一个都是spanCount的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) % (parent.layoutManager as GridLayoutManager).spanCount == 0) {
                outRect.left = 0
            }
            if (parent.getChildLayoutPosition(view) % (parent.layoutManager as GridLayoutManager).spanCount == (parent.layoutManager as GridLayoutManager).spanCount - 1) {
                outRect.right = 0
            }
        } else {
            outRect.set(0, 0, 0, mDividerHeight)
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDividerHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }
}