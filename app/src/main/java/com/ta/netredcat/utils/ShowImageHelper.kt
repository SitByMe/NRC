package com.ta.netredcat.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.ta.netredcat.BuildConfig
import com.ta.netredcat.R

class ShowImageHelper {
    companion object {
        fun loadCircle(mContext: Context, url: String, view: ImageView) {
            load(mContext, url, R.mipmap.ic_def_head, R.mipmap.ic_def_head, view)
        }

        fun loadHalfCircle(mContext: Context, url: String, view: ImageView) {
            load(
                mContext,
                url,
                R.mipmap.bg_def_half_circle,
                R.mipmap.bg_def_half_circle,
                view
            )
        }

        fun loadSquare(mContext: Context, url: String, view: ImageView) {
            load(mContext, url, R.mipmap.bg_def_square, R.mipmap.bg_def_square, view)
        }

        fun loadRect(mContext: Context, url: String, view: ImageView) {
            load(mContext, url, R.mipmap.bg_def_rect, R.mipmap.bg_def_rect, view)
        }

        private fun load(
            mContext: Context,
            url: String,
            @DrawableRes errorRes: Int,
            @DrawableRes placeholderRes: Int,
            view: ImageView
        ) {
            var u = url
            if (!u.startsWith("http")) {
                u = BuildConfig.BASE_IMG + u
            }
            Glide.with(mContext).load(u).error(errorRes)
                .placeholder(placeholderRes).into(view)
        }
    }
}