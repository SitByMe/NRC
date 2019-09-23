package com.ta.netredcat.view.gridimageview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleActivity
import com.ta.netredcat.view.gridimageview.photoutil.PhotoGalleyAdapter
import com.ta.netredcat.view.gridimageview.photoutil.PhotoSelectorHelper
import kotlinx.android.synthetic.main.activity_basetitle.*
import kotlinx.android.synthetic.main.activity_selector.*

import java.util.ArrayList

class SelectorActivity : BaseTitleActivity(), PhotoSelectorHelper.OnLoadPhotoListener {

    private var mAdapter: PhotoGalleyAdapter? = null

    override fun getTitleText(): String {
        return "选择图片"
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_selector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = PhotoGalleyAdapter(this)
        gridView.adapter = mAdapter
        PhotoSelectorHelper(this).getRecentPhotoList(this)

        initView()
    }

    private fun initView() {
        title_bar.tvRight.setOnClickListener {
            val list = ArrayList<String>()
            for (s in PhotoGalleyAdapter.mSelectedImage) {
                list.add(s)
            }
            if (list.size > 1) {
                //                showShort("最多选取9张图片");
                return@setOnClickListener
            }
            PhotoGalleyAdapter.mSelectedImage.clear()
            val intent = Intent()
            intent.putStringArrayListExtra("list", list)
            if (list.size == 0) {
                ToastUtils.showShort("请选择照片")
                return@setOnClickListener
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        title_bar.tvRight.text = "确定"
        title_bar.tvRight.setTextColor(resources.getColor(R.color.text_color_black1b))
        title_bar.tvRight.visibility = View.VISIBLE
    }

    override fun onPhotoLoaded(photos: List<String>) {
        mAdapter!!.notifyDataSetChanged(photos, true)
    }
}
