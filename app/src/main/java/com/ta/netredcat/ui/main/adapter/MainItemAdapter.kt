package com.ta.netredcat.ui.main.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

import com.ta.netredcat.R
import com.ta.netredcat.entity.result.HomeDataResult
import com.ta.netredcat.utils.ShowImageHelper
import java.lang.StringBuilder
import kotlin.math.ceil


class MainItemAdapter(
    private val mContext: Context,
    private val dataList: List<HomeDataResult.CategoryBean.NextCateBean>
) : RecyclerView.Adapter<MainItemAdapter.ContentViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(mContext)

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = inflater.inflate(R.layout.item_layout_main_item, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        ShowImageHelper.loadCircle(mContext, dataList[position].categoryImg, holder.ivItem)
        holder.ivIsNew.visibility = if (dataList[position].isNew()) View.VISIBLE else View.GONE
        holder.ivIsNew.measure(
            View.MeasureSpec.makeMeasureSpec(
                0,
                View.MeasureSpec.UNSPECIFIED
            ), View.MeasureSpec.makeMeasureSpec(
                0,
                View.MeasureSpec.UNSPECIFIED
            )
        )

        val beforeCateText = getBeforeCateText(holder.ivIsNew, holder.tvItem.paint)
        holder.tvItem.text = beforeCateText.plus(dataList[position].categoryName)

        holder.itemView.setOnClickListener { onItemClickListener?.onItemClick(dataList[position]) }
    }

    private fun getBeforeCateText(beforeView: View, paint: Paint): String {
        if (beforeView.visibility != View.VISIBLE) return ""
        val ivIsNewWidth = beforeView.measuredWidth
        val whiteSpaceWidth = getTextWidth(paint, " ")
        val wsCount: Int = ivIsNewWidth / whiteSpaceWidth + 1
        val beforeText = StringBuilder()
        for (i in 0 until wsCount) {
            beforeText.append(" ")
        }
        return beforeText.toString()
    }

    private fun getTextWidth(paint: Paint, str: String): Int {
        var iRet = 0
        if (str.isNotEmpty()) {
            val len: Int = str.length
            val widths = FloatArray(len)
            paint.getTextWidths(str, widths)
            for (i in 0 until len) {
                iRet += ceil(widths[i].toDouble()).toInt()
            }
        }
        return iRet
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItem: AppCompatImageView = itemView.findViewById(R.id.iv_item)
        val ivIsNew: AppCompatImageView = itemView.findViewById(R.id.iv_is_new)
        val tvItem: AppCompatTextView = itemView.findViewById(R.id.tv_item)
    }

    interface OnItemClickListener {
        fun onItemClick(subCateBean: HomeDataResult.CategoryBean.NextCateBean)
    }
}
