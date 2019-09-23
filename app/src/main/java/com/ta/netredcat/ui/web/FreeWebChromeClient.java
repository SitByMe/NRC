package com.ta.netredcat.ui.web;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class FreeWebChromeClient extends WebChromeClient {

    private ProgressBar progressBar;
    private String mTitle;

    public FreeWebChromeClient(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public FreeWebChromeClient(ProgressBar progressBar, String mTitle) {
        this.progressBar = progressBar;
        this.mTitle = mTitle;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
//        if (mTitle != null && !TextUtils.isEmpty(title)) {
//            mTitle.setTitle(title);
//        }
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (newProgress == 100) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(newProgress);
        }
    }

}
