package com.ta.netredcat.view.flipper

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdapterFlipperViewAdapter(strList: MutableList<String>, mActivity: AppCompatActivity) :
    BaseAdapter() {
    var mStrList = strList
    var mContext = mActivity

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemHolder: ItemHolder
        //CnPeng 2018/11/30 3:50 PM 这里略微有点怪，不能直接给 convertView复制，一直提示convertView是常量
        var itemView = convertView
        if (null == itemView) {
            itemView = TextView(mContext)
            itemHolder = ItemHolder()
            itemHolder.textView = itemView;
        } else {
            itemHolder = itemView.tag as ItemHolder
        }

        itemHolder.textView.text = mStrList[position]
        if (0 == position % 2) {
            itemHolder.textView.setTextColor(Color.BLACK)
        } else {
            itemHolder.textView.setTextColor(Color.parseColor("#ff0000"))
        }
        return itemView
    }

    override fun getItem(position: Int): Any {
        return mStrList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mStrList.size
    }

    class ItemHolder {
        lateinit var textView: TextView
    }
}