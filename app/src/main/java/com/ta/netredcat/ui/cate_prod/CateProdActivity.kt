package com.ta.netredcat.ui.cate_prod

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.App
import com.ta.netredcat.R
import com.ta.netredcat.entity.SubCateResult
import com.ta.netredcat.ui.BaseTitleMVPActivity
import com.ta.netredcat.ui.cate_prod.adapter.CateProdAdapter
import com.ta.netredcat.ui.recharge_must_see.RechargeMustSeeActivity
import com.ta.netredcat.utils.helper.StartActivityUtils
import kotlinx.android.synthetic.main.activity_cate_prod.*

class CateProdActivity : BaseTitleMVPActivity<CateProdPresenter>(), CateProdView {
    override fun setHeadRightText(mustSeeContent: String) {
        showRightText("充值必看", View.OnClickListener {
            val bundle = Bundle()
            bundle.putString(RechargeMustSeeActivity.EXTRA_CONTENT, mustSeeContent)
            StartActivityUtils.startActivity(RechargeMustSeeActivity::class.java, bundle)
        })
    }

    override fun setInputData(dataList: List<SubCateResult.InputKeywordsNew>) {
        for (it in dataList) {
            val inputView = AppCompatEditText(this)
            inputView.background = null
            inputView.hint = it.name
            inputView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            inputView.setHintTextColor(resources.getColor(R.color.text_color_hint))
            inputView.setTextColor(resources.getColor(R.color.text_color_black30))
            ll_input.addView(inputView)
        }
    }

    override fun setContentData(dataList: List<SubCateResult.Data>) {
        rv_content.layoutManager = GridLayoutManager(this, 2)
        val contentAdapter = CateProdAdapter(this, dataList)
        contentAdapter.setOnItemClickListern(View.OnClickListener {
            if (App.mApp.hasLogin(true)) {
                ToastUtils.showShort("sssssssssssssss")
            }
        })
        rv_content.adapter = contentAdapter
    }

    companion object {
        const val EXTRA_CATE_NAME = "extra_cate_name"
        const val EXTRA_CATE_ID = "extra_cate_id"
    }

    override fun getTitleText(): String = intent.extras?.getString(EXTRA_CATE_NAME).toString()

    override fun getContentLayoutId(): Int = R.layout.activity_cate_prod
    override fun initPresenter(): CateProdPresenter? {
        return CateProdPresenter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cateId = intent.extras?.getString(EXTRA_CATE_ID, "-1")
        presenter!!.requestCateProdData(cateId.toString())
    }
}
