package com.ta.netredcat.ui.web;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.ta.netredcat.BuildConfig;

public class ResizeWebView {

    private static Activity mActivity;
    private View mRootView; // setContentView对应的根布局
    private int mUsableHeight; //内容可见区域的可用高度
    private FrameLayout.LayoutParams mRootLayoutParams; //根布局的LayoutParams

    /**
     * 当软键盘弹出时重新调整View
     *
     * @param activity
     */
    public static void resizeOnPopKey(Activity activity) {
        mActivity = activity;
        new ResizeWebView(activity);
    }

    private ResizeWebView(Activity activity) {
        //获取可控区域内容的根view
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mRootView = content.getChildAt(0);
        mRootLayoutParams = (FrameLayout.LayoutParams) mRootView.getLayoutParams();
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                resizeContentView();
            }
        });
    }

    /**
     * 如果可用高度变化，重新计算根布局高度
     */
    private void resizeContentView() {
        int usableHeight = getUsableHeight();
        if (usableHeight != mUsableHeight) {
            //屏幕的高度
            int screenHeight = mRootView.getRootView().getHeight();
            //屏幕高度与可用高度间距
            int space = screenHeight - usableHeight;
            //间距如果大于屏幕的1/4则判断为软键盘弹出
            if (space > (screenHeight / 4)) {
                mRootLayoutParams.height = screenHeight - space;
            } else {
                mRootLayoutParams.height = screenHeight;
            }
            mRootView.requestLayout();
            mUsableHeight = usableHeight;
        }
    }

    /**
     * 内容可见区域的可用高度(不包括软键盘)
     *
     * @return
     */
    private int getUsableHeight() {
        Rect r = new Rect();
        mRootView.getWindowVisibleDisplayFrame(r);
        if (isTranslucentState(mActivity)) {
            //全屏的模式直接返回r.bottom
            return r.bottom;
        } else {
            //theme设置非全屏时需要减去状态栏的高度
            return (r.bottom - r.top);
        }
    }

    /**
     * 判断是否是透明状态栏
     *
     * @param activity
     * @return
     */
    private boolean isTranslucentState(Activity activity) {
        int flag = activity.getWindow().getAttributes().flags;
        if ((flag & WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                == WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) {
            return true;
        } else {
            return false;
        }
    }

    public static void initWebView(final WebView webView) {
        //debug
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }


        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        //WebView属性设置
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setAppCacheEnabled(true);
        settings.setLoadWithOverviewMode(true);

//        settings.setBuiltInZoomControls(true);
        settings.supportMultipleWindows();
        settings.setAllowContentAccess(true);
//        settings.setSaveFormData(true);
//        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        //webview在安卓5.0之前默认允许其加载混合网络协议内容
        // 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            webView.getSettings().setLoadsImagesAutomatically(false);
        }


        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
                return false;
            }
        });
    }


}
