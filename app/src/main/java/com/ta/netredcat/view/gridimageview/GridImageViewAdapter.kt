package com.ta.netredcat.view.gridimageview

import android.content.Context
import android.widget.ImageView

import androidx.annotation.DrawableRes

import com.ta.netredcat.R

/**
 * @param <T> 指定类型
</T> */
abstract class GridImageViewAdapter<T> {

    val showStyle: Int
        get() = GridImageView.STYLE_GRID

    abstract fun onDisplayImage(context: Context, imageView: ImageView, path: T)

    abstract fun onAddClick(context: Context, list: List<T>)

    open fun onItemImageClick(context: Context, index: Int, list: List<T>) {
    }

    @DrawableRes
    fun generateAddIcon(): Int {
        return R.mipmap.ic_feedback_add
    }
}