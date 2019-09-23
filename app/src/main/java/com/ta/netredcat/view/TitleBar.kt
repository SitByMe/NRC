package com.ta.netredcat.view

import android.app.Activity
import android.content.Context
import android.text.Spannable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatTextView
import com.ta.netredcat.R
import kotlinx.android.synthetic.main.layout_head.view.*

class TitleBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var root: View? = null
    lateinit var ivLeft: ImageView
    lateinit var tvTitle: TextView
    lateinit var ivRight: ImageView
    lateinit var tvRight: AppCompatTextView
    private var mLine: View? = null
    private var leftBack = true
    var activity: Activity? = null
    private var onTitleBarClickListener: OnTitleBarClickListener? = null
    private lateinit var onRightClickListener: OnRightClickListener

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        root = LayoutInflater.from(context).inflate(R.layout.layout_head, this, false)
        ivLeft = root!!.findViewById(R.id.iv_head_left)
        tvTitle = root!!.findViewById(R.id.tv_head_title)
        ivRight = root!!.findViewById(R.id.iv_head_right)
        tvRight = root!!.findViewById(R.id.tv_head_right)
        mLine = root!!.findViewById(R.id.line)
        addView(root)
    }

    fun setBarBackGroundColor(resId: Int) {
        root!!.setBackgroundColor(resources.getColor(resId))
    }

    fun setTitle(resId: Int): TitleBar {
        tvTitle.text = resources.getString(resId)
        return this
    }

    fun setTitleColr(colorId: Int): TitleBar {
        tvTitle.setTextColor(resources.getColor(colorId))
        return this
    }

    fun setTitle(title: CharSequence): TitleBar {
        tvTitle.text = title
        return this
    }

    fun setTitle(title: Spannable): TitleBar {
        tvTitle.text = title
        return this
    }

    fun setLeftIcon(resId: Int): TitleBar {
        ivLeft.setImageResource(resId)
        return this
    }

    fun setRightIcon(resId: Int, listener: OnRightClickListener): TitleBar {
        ivRight.setImageResource(resId)
        setOnRightClickListener(listener)
        return this
    }

    private fun setOnRightClickListener(listener: OnRightClickListener) {
        onRightClickListener = listener
    }

    fun setLineGone(visible: Boolean): TitleBar {
        if (mLine != null)
            mLine!!.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    fun setGone(visible: Boolean): TitleBar {
        if (root != null)
            root!!.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    fun setRightVisible(visible: Boolean): TitleBar {
        ivRight.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    fun setRightTvVisible(visible: Boolean): TitleBar {
        tvRight.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    fun showRightTv(text: String, listener: OnClickListener) {
        tv_head_right.text = text
        tv_head_right.setOnClickListener(listener)
    }

    override fun onClick(v: View) {
        if (leftBack && v.id == TitleClick.btnLeft) {
            finishActivity()
        } else if (v.id == TitleClick.btnRight || v.id == TitleClick.btnRightTv) {
            onRightClickListener.onClick(v)
        } else if (onTitleBarClickListener != null) {
            onTitleBarClickListener!!.onTitleBarClick(v)
        }
    }

    private fun finishActivity() {
        if (activity != null && !activity!!.isFinishing) {
            activity!!.finish()
        }
    }

    fun setOnTitleBarClickListener(onTitleBarClickListener: OnTitleBarClickListener) {
        this.onTitleBarClickListener = onTitleBarClickListener
    }

    interface OnTitleBarClickListener {
        fun onTitleBarClick(v: View)
    }

    interface OnRightClickListener {
        fun onClick(v: View)
    }

    interface TitleClick {
        companion object {
            const val btnLeft = R.id.iv_head_left
            const val btnRight = R.id.iv_head_right
            const val btnRightTv = R.id.tv_head_right
        }
    }
}