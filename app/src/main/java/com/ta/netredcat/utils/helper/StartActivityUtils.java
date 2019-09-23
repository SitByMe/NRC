package com.ta.netredcat.utils.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Size;

import com.blankj.utilcode.util.ActivityUtils;
import com.ta.netredcat.interface_enum.WEB_PAGE_TYPE;
import com.ta.netredcat.ui.web.WebPageActivity;

public class StartActivityUtils {
    public static void startActivity(Class<? extends Activity> cls) {
        ActivityUtils.startActivity(cls);
    }

    public static void startActivity(Class<? extends Activity> cls, Bundle bundle) {
        ActivityUtils.startActivity(bundle, cls);
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, Bundle bundle, int requestCode) {
        ActivityUtils.startActivityForResult(bundle, activity, cls, requestCode);
    }

    /**
     * 打开 Web Activity
     *
     * @param url url地址
     */
    private static void startWebActivity(String webTitle, String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("title", webTitle);
        ActivityUtils.startActivity(bundle, WebPageActivity.class);
//        Utils.startActivity(WebPageActivity.class, bundle);
    }

    /**
     * 打开系统浏览器
     *
     * @param context 上下文
     * @param url     url地址
     */
    private static void startSystemWeb(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri web_url = Uri.parse(url);
        intent.setData(web_url);
        context.startActivity(intent);
    }

    /**
     * 跳转网页
     *
     * @param context     上下文
     * @param webUrl      网址
     * @param webPageType 打开方式 1-内部 2-外部
     */
    public static void startToWeb(Context context, String webTitle, String webUrl, @Size(min = 1) @WEB_PAGE_TYPE int webPageType) {
        switch (webPageType) {
            case WEB_PAGE_TYPE.TYPE_INNER:
                StartActivityUtils.startWebActivity(webTitle, webUrl);
                break;
            case WEB_PAGE_TYPE.TYPE_OUTER:
                StartActivityUtils.startSystemWeb(context, webUrl);
                break;
        }
    }
}
