package com.ta.netredcat.interface_enum;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 打开方式 1-内部 2-外部
 */
@IntDef({WEB_PAGE_TYPE.TYPE_INNER, WEB_PAGE_TYPE.TYPE_OUTER})
@Retention(RetentionPolicy.SOURCE)
public @interface WEB_PAGE_TYPE {
    int TYPE_INNER = 1;
    int TYPE_OUTER = 2;
}
