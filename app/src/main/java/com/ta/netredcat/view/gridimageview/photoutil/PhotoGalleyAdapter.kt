package com.ta.netredcat.view.gridimageview.photoutil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.ta.netredcat.R

import java.util.ArrayList
import java.util.LinkedList

class PhotoGalleyAdapter @JvmOverloads constructor(
    private var mContext: Context,
    mModels: MutableList<String>? = null
) : BaseAdapter() {
    private val mList: MutableList<String>
    private var checkPos = -1
    private val mLayoutInflater: LayoutInflater

    init {
        var models = mModels
        mLayoutInflater = LayoutInflater.from(mContext)
        if (models == null) {
            models = ArrayList()
        }
        this.mList = models
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getItem(position: Int): String {

        return mList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView = if (convertView == null) {
            mLayoutInflater.inflate(R.layout.grid_photo_item, parent, false) as ImageView
        } else {
            (convertView as ImageView?)!!
        }
        val path = getItem(position)
        //        if(mSelectedImage.contains(path)){
        //            imageView.setColorFilter(0x80000000);
        //        }else {
        //            imageView.setColorFilter(null);
        //        }
        imageView.setOnClickListener { v ->
            setChecked(position)
            if (mSelectedImage.contains(path)) {
                mSelectedImage.remove(path)
                //                imageView.setColorFilter(null);
            } else {
                mSelectedImage.clear()
                mSelectedImage.add(path)
                //                imageView.setColorFilter(0x80000000);
            }
            notifyDataSetChanged()
        }

        Glide.with(mContext).load("file://$path").centerCrop().override(400, 400).into(imageView)
        /*if (mSelectedImage.size() >= 9) {
            imageView.setVisibility(View.GONE);
            imageView.setEnabled(false);
            ToastHelper.showShort(mContext, "最多选取9张图片");
        }*/
        if (checkPos == position) {
            imageView.setColorFilter(-0x80000000)
        } else {
            imageView.colorFilter = null
        }
        return imageView
    }

    private fun setChecked(checked: Int) {//设定一个选中的标志位，在activity中传入值。
        this.checkPos = checked
    }

    /**
     * 更新数据
     */
    fun notifyDataSetChanged(models: List<String>?, isRefresh: Boolean) {
        if (isRefresh) {
            this.mList.clear()
        }
        if (models == null || models.isEmpty()) {
            this.notifyDataSetChanged()
            return
        }
        this.mList.addAll(models)
        this.notifyDataSetChanged()
    }

    companion object {
        var mSelectedImage: MutableList<String> = LinkedList()
    }
}
