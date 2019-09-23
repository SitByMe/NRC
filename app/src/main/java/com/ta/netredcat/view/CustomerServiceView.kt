package com.ta.netredcat.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView
import com.ta.netredcat.R
import com.ta.netredcat.utils.ShowImageHelper
import de.hdodenhof.circleimageview.CircleImageView

class CustomerServiceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var root: View
    private lateinit var ivCsvIcon: CircleImageView
    private lateinit var tvText1: AppCompatTextView
    private lateinit var tvText2: AppCompatTextView

    init {
        initView()
    }

    private fun initView() {
        root = LayoutInflater.from(context)
            .inflate(R.layout.widget_layout_customer_service_view, this, false)
        ivCsvIcon = root.findViewById(R.id.iv_csv_icon)
        tvText1 = root.findViewById(R.id.tv_text1)
        tvText2 = root.findViewById(R.id.tv_text2)
        addView(root)
    }

    fun setIcon(@DrawableRes resId: Int) {
        ivCsvIcon.background = context.resources.getDrawable(resId)
    }

    fun setIconUrl(mContext: Context, iconUrl: String) {
        ShowImageHelper.loadCircle(mContext, iconUrl, ivCsvIcon)
    }

    fun setText1(text: String) {
        tvText1.text = text
    }

    fun setText2(text: String) {
        tvText2.text = text
    }
}