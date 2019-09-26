package com.ta.netredcat.data.net.iservice;

import com.ta.netredcat.entity.result.LoginResult;
import com.ta.netredcat.entity.result.UserInfoResult;
import com.ta.netredcat.entity.result.MyFeedBackResult;
import com.tianao.module.net.entity.httpResult.AppResult;
import com.tianao.module.net.entity.httpResult.EmptyBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUserService {

    /**
     * 获取图形效验码
     *
     * @param deviceId 设备号
     * @param time     随机数
     */
    @GET("api/captcha/{device_id}")
    Observable<ResponseBody> requestCheckCode(@Path("device_id") String deviceId, @Query("time") String time);

    /**
     * 发送验证码
     *
     * @param phone    电话号码
     * @param code     图形验证码
     * @param deviceNo 设备号
     * @param type     1-注册 2-修改忘记密码
     */
    @FormUrlEncoded
    @POST("api/msgCode")
    Observable<AppResult<EmptyBean>> sendCode(@Field("phone") String phone, @Field("code") String code, @Field("device_no") String deviceNo, @Field("type") String type);

    /**
     * 登录
     *
     * @param phone      电话号码
     * @param verifyCode 验证码
     * @param invCode    邀请码
     */
    @FormUrlEncoded
    @POST("api/login")
    Observable<AppResult<LoginResult>> login(@Field("phone") String phone, @Field("code") String verifyCode, @Field("invite_code") String invCode);

    /**
     * 登录
     *
     * @param phone      电话号码
     * @param verifyCode 验证码
     */
    @FormUrlEncoded
    @POST("api/login")
    Observable<AppResult<LoginResult>> login(@Field("phone") String phone, @Field("code") String verifyCode);

    /**
     * 获取用户信息
     */
    @FormUrlEncoded
    @POST("api/myInfo")
    Observable<AppResult<UserInfoResult>> requestUserInfo(@Field("useless") String useless);

    /**
     * 获取我的反馈意见
     */
    @FormUrlEncoded
    @POST("api/msgList")
    Observable<AppResult<MyFeedBackResult>> requestMyFeedBack(@Field("useless") String useless);

    /**
     * 修改用户资料
     *
     * @param face      用户头像
     * @param nickname  用户昵称
     * @param autograph 签名
     */
    @FormUrlEncoded
    @POST("api/editProfile")
    Observable<AppResult<EmptyBean>> modifyUserInfo(@Field("face") String face, @Field("nickname") String nickname, @Field("autograph") String autograph);
}
