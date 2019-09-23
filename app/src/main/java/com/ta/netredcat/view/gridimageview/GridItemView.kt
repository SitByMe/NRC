package com.ta.netredcat.view.gridimageview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent

import androidx.appcompat.widget.AppCompatImageView

import com.ta.netredcat.R

class GridItemView : AppCompatImageView {
    private var mDelClickL: OnDelButtonClickL? = null//监听删除
    private var mPaint: Paint? = null//画笔
    private var mDelBitmap: Bitmap? = null//删除图片
    private var mDelBound: Rect? = null//删除矩形框
    private var mDelBoundPadding = 5//默认为5

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        //mPaint.setColor(0x88000000);
        mDelBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_feedback_del)
        mDelBound = Rect()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mDelBound!!.set(
            width - paddingRight - mDelBitmap!!.width - mDelBoundPadding * 2,
            paddingTop,
            width - paddingRight,
            paddingTop + mDelBitmap!!.height + mDelBoundPadding * 2
        )
        // canvas.drawRect(mDelBound,mPaint);//先画一个删除框
        canvas.drawBitmap(
            mDelBitmap!!,
            (mDelBound!!.left + mDelBoundPadding).toFloat(),
            (mDelBound!!.top + mDelBoundPadding).toFloat(),
            null
        )//然后画一个删除按钮
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            val touchable = (event.x > mDelBound!!.left
                    && event.x < mDelBound!!.right && event.y > mDelBound!!.top && event.y < mDelBound!!.bottom)

            if (touchable && mDelClickL != null) {//点击删除键
                mDelClickL!!.onDelClickL()
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    fun setDelBoundPadding(boundPadding: Int) {
        this.mDelBoundPadding = boundPadding
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        setImageDrawable(null)
    }

    fun setOnDelClickL(l: OnDelButtonClickL) {
        this.mDelClickL = l
    }

    interface OnDelButtonClickL {
        fun onDelClickL()
    }
}