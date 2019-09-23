package com.ta.netredcat.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.ta.netredcat.R
import com.ta.netredcat.utils.DensityUtil

class FeedBackContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private lateinit var root: View
    private lateinit var etTextContent: AppCompatEditText
    private lateinit var llImgContent: LinearLayout
    private lateinit var tvTextCount: AppCompatTextView

    init {
        initView()
    }

    private fun initView() {
        root =
            LayoutInflater.from(context).inflate(R.layout.widget_layout_feed_back_view, this, false)
        etTextContent = root.findViewById(R.id.et_text_content)
        llImgContent = root.findViewById(R.id.ll_img_content)
        tvTextCount = root.findViewById(R.id.tv_text_count)
        llImgContent.addView(createImg())
        etTextContent.setText( "请详细描述遇到的问题，以便我们及时为你解决！")
    }

    private fun createImg(): AppCompatImageView {
        val imgView = AppCompatImageView(context)
        imgView.layoutParams.width = DensityUtil.dp2px(50f)
        imgView.layoutParams.height = DensityUtil.dp2px(50f)
        imgView.scaleType = ImageView.ScaleType.CENTER_CROP
        imgView.setBackgroundResource(R.mipmap.bg_default_feed_img)
        return imgView
    }
}