package com.ta.netredcat.ui.cate_prod.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ta.netredcat.R
import com.ta.netredcat.entity.SubCateResult

class CateProdAdapter(
    mContext: Context,
    private val dataList: List<SubCateResult.Data>
) :
    RecyclerView.Adapter<CateProdAdapter.ContentViewHolder>() {
    private var inflater: LayoutInflater = LayoutInflater.from(mContext)

    private lateinit var onItemClickListener: View.OnClickListener

    fun setOnItemClickListern(onItemClickListener: View.OnClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view: View = inflater.inflate(R.layout.item_layout_cate_prod, parent, false)
        return ContentViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.tvPrice.text =
            dataList[position].price.substring(0, dataList[position].price.lastIndexOf("."))
        holder.tvMsg.text = dataList[position].times.toString().plus(dataList[position].name)
        onItemClickListener.let { holder.itemView.setOnClickListener(onItemClickListener) }
    }

    class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivBg = view.findViewById<AppCompatImageView>(R.id.iv_bg)!!
        var tvPrice = view.findViewById<AppCompatTextView>(R.id.tv_price)!!
        var tvMsg = view.findViewById<AppCompatTextView>(R.id.tv_msg)!!
    }

    interface OnItemClickListener {
        fun itemClick(item: SubCateResult.Data)
    }
}