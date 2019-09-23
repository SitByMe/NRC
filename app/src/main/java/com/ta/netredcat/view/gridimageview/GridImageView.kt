package com.ta.netredcat.view.gridimageview

import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.ImageView
import android.widget.Scroller

import java.util.ArrayList
import kotlin.math.abs
import kotlin.math.ceil

class GridImageView<T> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ViewGroup(context, attrs, defStyle) {
    private var mShowStyle = STYLE_GRID     // 显示风格，默认是网格风格

    private var mAdapter: GridImageViewAdapter<T>? = null
    private val mImgDataList = ArrayList<T>()
    private var mGap = 5 //间隙,默认为5px
    private val mColumnCount = 1 // 列数
    private var mGridSize: Int = 0 //每个条目的大小
    private val mAddView: ImageView//添加图片的按钮

    /**
     * 处理滑动的
     */
    private val mScroller: Scroller?
    private var mVelocityTracker: VelocityTracker? = null
    private val mTouchSlop: Int
    private val minFlingSpeed: Int
    private val maxFlingSpeed: Int
    private var mLeftBorder: Int = 0
    private var mRightBorder: Int = 0

    val imgDataList: List<T>
        get() = mImgDataList

    private var mLastX: Float = 0.toFloat()//记录上次滑动的位置

    init {
        //初始化Scroller实例
        mScroller = Scroller(context)
        //初始化参数
        mTouchSlop = ViewConfiguration.get(getContext()).scaledTouchSlop
        minFlingSpeed = ViewConfiguration.get(getContext()).scaledMinimumFlingVelocity
        maxFlingSpeed = ViewConfiguration.get(getContext()).scaledMaximumFlingVelocity

        mAddView = ImageView(context)
        //添加mAddView
        mAddView.setOnClickListener { v ->
            if (mAdapter != null) {
                mAdapter!!.onAddClick(getContext(), mImgDataList)
            }
        }
        addView(mAddView, generateDefaultLayoutParams())
    }

    /**
     * 测量GridImageView占据的大小
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (mShowStyle == STYLE_HORIZONTAL) {
            measureHorizontal(widthMeasureSpec, heightMeasureSpec)
        } else {
            measureVertical(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (mShowStyle == STYLE_HORIZONTAL) {
            layoutHorizontal(changed, l, t, r, b)
        } else {
            layoutVertical(changed, l, t, r, b)
        }
    }

    private fun measureHorizontal(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height: Int = mGridSize + paddingTop + paddingBottom
        val totalWidth = width - paddingLeft - paddingRight
        mGridSize = (totalWidth - mGap * (mColumnCount - 1)) / mColumnCount //算出每个条目的大小，以宽度为标准。
        //计算出高度
        setMeasuredDimension(width, height)
    }

    private fun measureVertical(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        var height: Int
        val totalWidth = width - paddingLeft - paddingRight
        val totalCount = mImgDataList.size + 1//把mAddView也给算进去
        mGridSize = (totalWidth - mGap * (mColumnCount - 1)) / mColumnCount //算出每个条目的大小，以宽度为标准。
        mGridSize = 100
        val mRowCount = ceil(totalCount * 1.0 / mColumnCount).toInt()//算出行数
        height = mGridSize * mRowCount + mGap * (mRowCount - 1) + paddingTop + paddingBottom//计算出高度
        height = 100
        setMeasuredDimension(width, height)
    }

    private fun layoutHorizontal(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val childrenCount = mImgDataList.size
        for (i in 0 until childrenCount) {
            val childrenView = getChildAt(i) as ImageView
            if (mAdapter != null) {
                mAdapter!!.onDisplayImage(context, childrenView, mImgDataList[i])
            }
            val left = (mGridSize + mGap) * i + paddingLeft
            val top = paddingTop
            val right = left + mGridSize
            val bottom = top + mGridSize
            childrenView.layout(left, top, right, bottom)
        }

        val left = (mGridSize + mGap) * childrenCount + paddingLeft
        val top = paddingTop
        val right = left + mGridSize
        val bottom = top + mGridSize
        mAddView.layout(left, top, right, bottom)//调整mAddView的位置

        // 初始化左右边界值
        mLeftBorder = getChildAt(0).left
        mRightBorder = getChildAt(childrenCount).right

        if (mRightBorder - mLeftBorder > width) {
            scrollTo(mRightBorder - width, 0)
        } else {
            scrollTo(mLeftBorder, 0)
        }
    }

    private fun layoutVertical(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val childrenCount = mImgDataList.size
        for (i in 0 until childrenCount) {
            val childrenView = getChildAt(i) as ImageView
            if (mAdapter != null) {
                mAdapter!!.onDisplayImage(context, childrenView, mImgDataList[i])
            }
            val rowNum = i / mColumnCount
            val columnNum = i % mColumnCount
            val left = (mGridSize + mGap) * columnNum + paddingLeft
            val top = (mGridSize + mGap) * rowNum + paddingTop
            val right = left + mGridSize
            val bottom = top + mGridSize
            childrenView.layout(left, top, right, bottom)
        }

        val rowNum = childrenCount / mColumnCount
        val columnNum = childrenCount % mColumnCount
        val left = (mGridSize + mGap) * columnNum + paddingLeft
        val top = (mGridSize + mGap) * rowNum + paddingTop
        val right = left + mGridSize
        val bottom = top + mGridSize
        mAddView.layout(left, top, right, bottom)//调整mAddView的位置
    }

    override fun computeScroll() {
        if (mScroller!!.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            postInvalidate()
        }
    }

    /**
     * 设置图片数据集合
     *
     * @param lists         数据集合
     * @param clearLastData 是否清理上次的数据
     */
    fun setImageData(lists: List<T>?, clearLastData: Boolean) {
        if (lists == null || lists.isEmpty()) {
            return
        }
        if (clearLastData) {
            mImgDataList.clear()
        }
        mImgDataList.addAll(lists)
        if (mImgDataList.size == 9) {
            mAddView.visibility = View.GONE
        }
        refreshDataSet()
    }

    private fun refreshDataSet() {//根据数据，调整ImageView的数量
        val oldViewCount = childCount//上次的item个数
        val newViewCount = mImgDataList.size + 1//这次的item个数
        if (oldViewCount > newViewCount) {
            removeViews(newViewCount - 1, oldViewCount - newViewCount)//位置减去1，以免移除mAddView
        } else if (oldViewCount < newViewCount) {
            for (i in oldViewCount until newViewCount) {
                val iv = getImageView(i - 1)//索引减去1，保证单击事件位置的准确性。
                addView(iv, i - 1, generateDefaultLayoutParams())//索引减去1，保证添加在mAddView之前
            }
        }
        requestLayout()
    }

    /**
     * 获得 ImageView
     * 保证了 ImageView 的重用
     *
     * @param position 位置
     */
    private fun getImageView(position: Int): GridItemView {
        val imageView = GridItemView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setOnClickListener {
            if (mAdapter != null) {
                mAdapter!!.onItemImageClick(context, position, mImgDataList)
            }
        }

        imageView.setOnDelClickL(object : GridItemView.OnDelButtonClickL {
            override fun onDelClickL() {
                mImgDataList.removeAt(position)
                refreshDataSet()
            }
        })
        return imageView
    }

    /**
     * 设置适配器
     *
     * @param adapter 适配器
     */
    fun setAdapter(adapter: GridImageViewAdapter<T>) {
        mAdapter = adapter
        mAddView.setImageResource(adapter.generateAddIcon())
        mShowStyle = adapter.showStyle
    }

    /**
     * 设置宫格间距
     *
     * @param gap 宫格间距 px
     */
    fun setGap(gap: Int) {
        mGap = gap
    }

    /**
     * 请除所有数据
     */
    fun clear() {
        mImgDataList.clear()
        refreshDataSet()
    }

    fun remove(position: Int) {
        mImgDataList.removeAt(position)
        refreshDataSet()
    }

    fun add(t: T) {
        mImgDataList.add(t)
        refreshDataSet()
    }

    fun addAll(l: List<T>) {
        mImgDataList.addAll(l)
        refreshDataSet()
    }

    //===============================以下为滚动效果相关=============================

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        //触摸点
        val x = event.x
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (mScroller != null) {
                    if (!mScroller.isFinished) {
                        mScroller.abortAnimation()
                    }
                }
                mLastX = x //记住开始落下的屏幕点
            }
            MotionEvent.ACTION_MOVE -> {
                val detaX = (x - mLastX).toInt()
                if (abs(detaX) > mTouchSlop && mShowStyle == STYLE_HORIZONTAL) {
                    return true
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var isTouch = false
        acquireVelocityTracker(event)
        //触摸点
        val x = event.x
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (mScroller != null) {
                    if (!mScroller.isFinished) {
                        mScroller.abortAnimation()
                    }
                }
                mLastX = x
                isTouch = false
            }
            MotionEvent.ACTION_MOVE -> {
                val detaX = (mLastX - x).toInt() //每次滑动屏幕，屏幕应该移动的距离
                if (scrollX + detaX < mLeftBorder) {//判断有没有划出边界，如果划出便还原。
                    scrollTo(mLeftBorder, 0)
                } else if (scrollX + width + detaX > mRightBorder) {
                    if (mRightBorder - mLeftBorder > width) {
                        scrollTo(mRightBorder - width, 0)

                    } else {
                        scrollTo(mLeftBorder, 0)
                    }
                } else {
                    scrollBy(detaX, 0)
                }

                mLastX = x
                isTouch = true
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                isTouch = false
                mVelocityTracker!!.computeCurrentVelocity(1000, maxFlingSpeed.toFloat())
                val speed = mVelocityTracker!!.xVelocity.toInt()
                if (Math.abs(speed) > minFlingSpeed) {
                    mScroller!!.fling(
                        scrollX,
                        0,
                        -speed,
                        0,
                        mLeftBorder,
                        mRightBorder - width,
                        0,
                        0
                    )
                    invalidate()
                }
                releaseVelocityTracker()
            }
        }
        return isTouch
    }

    private fun acquireVelocityTracker(event: MotionEvent) {
        if (null == mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain()
        }
        mVelocityTracker!!.addMovement(event)
    }

    private fun releaseVelocityTracker() {
        if (null != mVelocityTracker) {
            mVelocityTracker!!.clear()
            mVelocityTracker!!.recycle()
            mVelocityTracker = null
        }
    }

    companion object {
        val STYLE_GRID = 0     // 网格风格
        val STYLE_HORIZONTAL = 1     // 水平风格
    }
}

