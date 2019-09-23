package com.ta.netredcat.ui.main.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ActivityUtils

import com.ta.netredcat.R
import com.ta.netredcat.entity.HomeDataResult
import com.ta.netredcat.ui.cate_prod.CateProdActivity
import com.ta.netredcat.utils.ShowImageHelper
import com.ta.netredcat.view.recyclerview.SimpleDividerItemDecoration

class MainContentAdapter(
    private val mContext: Context,
    private val dataList: List<HomeDataResult.CategoryBean>
) : RecyclerView.Adapter<MainContentAdapter.ContentViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = inflater.inflate(R.layout.item_layout_main_content, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        ShowImageHelper.loadSquare(mContext, dataList[position].categoryImg, holder.ivGroup)
        holder.tvGroup.text = dataList[position].categoryName
        holder.ivIsNew.visibility = if (dataList[position].isNew()) View.VISIBLE else View.GONE
        holder.rvItem.layoutManager = GridLayoutManager(mContext, 3)
        holder.rvItem.addItemDecoration(SimpleDividerItemDecoration(mContext, 18))
        val itemA = dataList[position].nextCate?.let { MainItemAdapter(mContext, it) }
        itemA?.onItemClickListener = object : MainItemAdapter.OnItemClickListener {
            override fun onItemClick(subCateBean: HomeDataResult.CategoryBean.NextCateBean) {
                val bundle = Bundle()
                bundle.putString(CateProdActivity.EXTRA_CATE_NAME, dataList[position].categoryName)
                bundle.putString(CateProdActivity.EXTRA_CATE_ID, subCateBean.id.toString())
                ActivityUtils.startActivity(bundle, CateProdActivity::class.java)
            }
        }
        holder.rvItem.adapter = itemA

    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGroup: AppCompatImageView = itemView.findViewById(R.id.iv_group)
        val tvGroup: AppCompatTextView = itemView.findViewById(R.id.tv_group)
        val rvItem: RecyclerView = itemView.findViewById(R.id.rv_item)
        val ivIsNew: AppCompatImageView = itemView.findViewById(R.id.iv_is_new)
    }
}
