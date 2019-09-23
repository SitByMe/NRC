package com.ta.netredcat.ui.cate_prod

import android.content.Context
import com.ta.netredcat.data.net.AppObserver
import com.ta.netredcat.data.net.method.ProdHttpMethods
import com.ta.netredcat.entity.SubCateResult
import com.ta.netredcat.ui.mvp.AppDataManager
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class CateProdDataManager(mContext: Context) : AppDataManager(mContext, null, null, null) {
    fun requestCateProdData(
        cateId: String,
        before: Consumer<Disposable>,
        observer: AppObserver<SubCateResult>
    ) {
        ProdHttpMethods.requestCateProdData(cateId, before, observer)
    }
}