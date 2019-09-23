package com.ta.netredcat.data.net.iservice;

import com.ta.netredcat.entity.HomeDataResult;
import com.ta.netredcat.entity.ServiceBeanResult;
import com.tianao.module.net.entity.httpResult.AppResult;
import com.tianao.module.net.entity.httpResult.EmptyBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPubService {
    /**
     * 获取首页数据
     *
     * @param params 参数
     */
    @FormUrlEncoded
    @POST("api/home")
    Observable<AppResult<HomeDataResult>> requestHomeData(@FieldMap Map<String, Object> params);

    /**
     * 获取客服列表数据接口
     */
    @FormUrlEncoded
    @POST("api/customer")
    Observable<AppResult<List<ServiceBeanResult>>> requestCustomerServiceList(@Field("useless") String useless);

    /**
     * 添加意见反馈
     */
    @FormUrlEncoded
    @POST("api/userMsg")
    Observable<AppResult<EmptyBean>> commitFeedBack(@Field("content") String content, @Field("type") String type, @Field("image") String image);
}
