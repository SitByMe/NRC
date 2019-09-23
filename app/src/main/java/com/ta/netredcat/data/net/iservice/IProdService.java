package com.ta.netredcat.data.net.iservice;

import com.ta.netredcat.entity.SubCateResult;
import com.tianao.module.net.entity.httpResult.AppResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IProdService {
    /**
     * 获取二级分类价格表
     */
    @FormUrlEncoded
    @POST("api/catePriceList")
    Observable<AppResult<SubCateResult>> requestCateProdData(@Field("category_type")String categoryType, @Field("cate_id") String cateId);
}
