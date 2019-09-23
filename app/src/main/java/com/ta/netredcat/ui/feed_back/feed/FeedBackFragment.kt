package com.ta.netredcat.ui.feed_back.feed

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.ta.netredcat.R
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.data.net.method.PubHttpMethods
import com.ta.netredcat.view.ClickProxy
import com.ta.netredcat.view.gridimageview.GridImageView
import com.ta.netredcat.view.gridimageview.GridImageViewAdapter
import com.ta.netredcat.view.gridimageview.SelectorActivity
import com.tianao.module.net.entity.httpResult.AppResult
import com.tianao.module.net.entity.httpResult.EmptyBean
import com.tianao.module.net.http.ObserverOnNextListener
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_layout_feed_back.*

class FeedBackFragment : Fragment() {
    lateinit var gridImageView: GridImageView<String>
    private var photoList: List<String> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_layout_feed_back, container, false)
        gridImageView = view.findViewById(R.id.gridView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        gridImageView.setAdapter(object : GridImageViewAdapter<String>() {
            override fun onAddClick(context: Context, list: List<String>) {
                val intent = Intent(activity, SelectorActivity::class.java)
                startActivityForResult(intent, 1234)
            }

            override fun onDisplayImage(context: Context, imageView: ImageView, path: String) {
                Glide.with(context).load("file://$path").centerCrop().override(200, 200)
                    .into(imageView)
            }

            override fun onItemImageClick(context: Context, index: Int, list: List<String>) {
                super.onItemImageClick(context, index, list)
                ToastUtils.showShort("--->$index")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        content.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                textNum.text = "${p0?.length}/100"
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        btn_commit.setOnClickListener(ClickProxy(View.OnClickListener { submit() }))
    }

    private fun submit() {
        val feedback = content.text.toString()
        if (TextUtils.isEmpty(feedback)) {
            ToastUtils.showShort("请输入遇到的问题")
            return
        }
        ToastUtils.showShort("提交")

        if (!TextUtils.isEmpty(photo)) {
        } else {
            PubHttpMethods.commitFeedBack(
                content.text.toString(), "问题类型1", photo.toString(),
                Consumer { },
                AppObserver<EmptyBean>(object : ObserverOnNextListener<EmptyBean>() {
                    override fun onNext(result: AppResult<EmptyBean>?) {
                    }
                })
            )
        }
    }

    private var photo: String? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                1234 -> {
                    if (data != null) {
                        val list: List<String>? = data.getStringArrayListExtra("list")
                        //  PictureUtil.cropPhoto(this, Uri.parse("file://"+list.get(0)));
                        gridImageView.setImageData(list, true)
                        photoList = gridImageView.imgDataList
                        for (i in photoList.indices) {
                            photo = photoList[i]
                        }
                    }
                }
            }
        }
    }
}