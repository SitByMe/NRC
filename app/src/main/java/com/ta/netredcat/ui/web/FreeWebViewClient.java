package com.ta.netredcat.ui.web;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FreeWebViewClient extends WebViewClient {

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        //handler.cancel(); // Android默认的处理方式
        handler.proceed();  // 接受所有网站的证书  授权证书()
    }
}
