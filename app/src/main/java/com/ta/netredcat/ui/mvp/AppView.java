package com.ta.netredcat.ui.mvp;

public interface AppView {
    void showLoadingView();

    void showLoadingView(boolean lightTextMode);

    void dismissLoadingView();

    void finishRefresh();

    void finishActivity();

    void showToast(String toastStr);

    void setRootUIVisibility(int visibility);

    /**
     * 添加网络错误Layout
     */
    void addNetErrorLayout();

    /**
     * 移除网络错误Layout
     */
    void removeNetErrorLayout();
}
