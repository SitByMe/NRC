package com.ta.netredcat.ui.mine

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.R
import com.ta.netredcat.entity.ServiceBeanResult
import com.ta.netredcat.view.CustomerServiceView

class CustomerServiceAdapter(
    private val mContext: Context,
    private val dataList: List<ServiceBeanResult>
) :
    RecyclerView.Adapter<CustomerServiceAdapter.ContentViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = inflater.inflate(R.layout.item_layout_coutomer_service, parent, false)
        return ContentViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.count()

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.csv.setIconUrl(mContext, dataList[position].customerIcon)
        holder.csv.setText1(dataList[position].customerName)
        holder.csv.setText2(dataList[position].customerNumber)

        holder.itemView.setOnClickListener {
            ToastUtils.showShort(
                dataList[position].customerName.plus(
                    "-"
                ).plus(dataList[position].customerNumber).plus("-").plus(dataList[position].type)
            )
        }
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val csv: CustomerServiceView = itemView.findViewById(R.id.csv)
    }
}