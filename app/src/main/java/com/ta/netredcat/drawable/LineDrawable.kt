package com.ta.netredcat.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

class LineDrawable(color: Int) : Drawable() {
    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = color
    }

    override fun draw(canvas: Canvas) {
        val rect = bounds
        canvas.drawLine(
            rect.left.toFloat(),
            rect.top.toFloat(),
            rect.right.toFloat(),
            rect.bottom.toFloat(),
            mPaint
        )
/*
        canvas.drawCircle(
            rect.exactCenterX(),
            rect.exactCenterY(),
            Math.min(rect.exactCenterX(), rect.exactCenterY()),
            mPaint
        )
*/
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
        invalidateSelf()
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        //not sure, so be safe
        return PixelFormat.TRANSLUCENT
    }
}