package com.ta.netredcat.constant;

/**
 * Http请求状态码常量
 */
public class StateConstants {
    /**
     * 成功
     */
    public static final int SUCCESS = 200;
    /**
     * 失败
     */
    public static final int FAILED = 0;
    /**
     * 远端请求失败
     */
    public static final int HOST_FAILED = 500;
    /**
     * 请求的内容已存在
     */
    public static final int CONTENT_EXISTED = 305;
    /**
     * 账号已在别处登录
     */
    public static final int TOKEN_ERROR = 444;
    /**
     * 登录过期
     */
    public static final int LOGIN_OVERDUE = 445;
}
