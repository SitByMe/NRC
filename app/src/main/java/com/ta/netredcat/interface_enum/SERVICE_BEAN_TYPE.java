package com.ta.netredcat.interface_enum;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 客服类型 1-QQ 2-微信
 */
@IntDef({SERVICE_BEAN_TYPE.QQ, SERVICE_BEAN_TYPE.WE_CHAT})
@Retention(RetentionPolicy.SOURCE)
public @interface SERVICE_BEAN_TYPE {
    int QQ = 1;
    int WE_CHAT = 2;
}
