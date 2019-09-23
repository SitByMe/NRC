package com.ta.netredcat.view.refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.ArrowDrawable;
import com.scwang.smartrefresh.layout.internal.InternalClassics;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;
import com.ta.netredcat.R;
import com.ta.netredcat.utils.DensityUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * 经典下拉头部
 * Created by SCWANG on 2017/5/28.
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class MyClassicsHeader extends InternalClassics<MyClassicsHeader> implements RefreshHeader {

    public static final int ID_TEXT_UPDATE = com.scwang.smartrefresh.layout.R.id.srl_classics_update;

    public static String REFRESH_HEADER_PULLING = null;//"下拉可以刷新";
    public static String REFRESH_HEADER_REFRESHING = null;//"正在刷新...";
    public static String REFRESH_HEADER_LOADING = null;//"正在加载...";
    public static String REFRESH_HEADER_RELEASE = null;//"释放立即刷新";
    public static String REFRESH_HEADER_FINISH = null;//"刷新完成";
    public static String REFRESH_HEADER_FAILED = null;//"刷新失败";
    public static String REFRESH_HEADER_UPDATE = null;//"上次更新 M-d HH:mm";
    public static String REFRESH_HEADER_SECONDARY = null;//"释放进入二楼";
//    public static String REFRESH_HEADER_UPDATE = "'Last update' M-d HH:mm";

    protected String KEY_LAST_UPDATE_TIME = "LAST_UPDATE_TIME";

    protected Date mLastTime;
    protected TextView mLastUpdateText;
    protected SharedPreferences mShared;
    protected DateFormat mLastUpdateFormat;
    protected boolean mEnableLastTime = true;

    protected String mTextPulling;//"下拉可以刷新";
    protected String mTextRefreshing;//"正在刷新...";
    protected String mTextLoading;//"正在加载...";
    protected String mTextRelease;//"释放立即刷新";
    protected String mTextFinish;//"刷新完成";
    protected String mTextFailed;//"刷新失败";
    protected String mTextUpdate;//"上次更新 M-d HH:mm";
    protected String mTextSecondary;//"释放进入二楼";

    //<editor-fold desc="RelativeLayout">
    public MyClassicsHeader(Context context) {
        this(context, null);
    }

    public MyClassicsHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyClassicsHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context, com.scwang.smartrefresh.layout.R.layout.srl_classics_header, this);

//        mLastUpdateText = new TextView(context);
//        mLastUpdateText.setTextColor(0xff7c7c7c);

        final View thisView = this;
        final View arrowView = mArrowView = thisView.findViewById(com.scwang.smartrefresh.layout.R.id.srl_classics_arrow);
        final View updateView = mLastUpdateText = thisView.findViewById(com.scwang.smartrefresh.layout.R.id.srl_classics_update);
        final View progressView = mProgressView = thisView.findViewById(com.scwang.smartrefresh.layout.R.id.srl_classics_progress);
//        final ViewGroup centerLayout = mCenterLayout;
        final DensityUtil density = new DensityUtil();

        mTitleText = thisView.findViewById(com.scwang.smartrefresh.layout.R.id.srl_classics_title);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ClassicsHeader);

        LayoutParams lpArrow = (LayoutParams) arrowView.getLayoutParams();
        LayoutParams lpProgress = (LayoutParams) progressView.getLayoutParams();
        LinearLayout.LayoutParams lpUpdateText = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lpUpdateText.topMargin = ta.getDimensionPixelSize(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextTimeMarginTop, density.dip2px(0));
        lpProgress.rightMargin = ta.getDimensionPixelSize(com.scwang.smartrefresh.layout.R.styleable.ClassicsFooter_srlDrawableMarginRight, density.dip2px(20));
        lpArrow.rightMargin = lpProgress.rightMargin;

        lpArrow.width = ta.getLayoutDimension(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableArrowSize, lpArrow.width);
        lpArrow.height = ta.getLayoutDimension(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableArrowSize, lpArrow.height);
        lpProgress.width = ta.getLayoutDimension(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableProgressSize, lpProgress.width);
        lpProgress.height = ta.getLayoutDimension(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableProgressSize, lpProgress.height);

        lpArrow.width = ta.getLayoutDimension(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableSize, lpArrow.width);
        lpArrow.height = ta.getLayoutDimension(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableSize, lpArrow.height);
        lpProgress.width = ta.getLayoutDimension(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableSize, lpProgress.width);
        lpProgress.height = ta.getLayoutDimension(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableSize, lpProgress.height);

        mFinishDuration = ta.getInt(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlFinishDuration, mFinishDuration);
        mEnableLastTime = ta.getBoolean(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlEnableLastTime, mEnableLastTime);
        mSpinnerStyle = SpinnerStyle.values[ta.getInt(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlClassicsSpinnerStyle, mSpinnerStyle.ordinal)];

        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableArrow)) {
            mArrowView.setImageDrawable(ta.getDrawable(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableArrow));
        } else if (mArrowView.getDrawable() == null) {
            mArrowDrawable = new ArrowDrawable();
            mArrowDrawable.setColor(0xff666666);
            mArrowView.setImageDrawable(mArrowDrawable);
        }

        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableProgress)) {
            mProgressView.setImageDrawable(ta.getDrawable(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlDrawableProgress));
        } else if (mProgressView.getDrawable() == null) {
            mProgressDrawable = new ProgressDrawable();
            mProgressDrawable.setColor(0xff666666);
            mProgressView.setImageDrawable(mProgressDrawable);
        }

        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextSizeTitle)) {
            mTitleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextSizeTitle, DensityUtil.dp2px(16)));
//        } else {
//            mTitleText.setTextSize(16);
        }

        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextSizeTime)) {
            mLastUpdateText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextSizeTime, DensityUtil.dp2px(12)));
//        } else {
//            mLastUpdateText.setTextSize(12);
        }

        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlPrimaryColor)) {
            super.setPrimaryColor(ta.getColor(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlPrimaryColor, 0));
        }
        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlAccentColor)) {
            setAccentColor(ta.getColor(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlAccentColor, 0));
        }

        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextPulling)) {
            mTextPulling = ta.getString(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextPulling);
        } else if (REFRESH_HEADER_PULLING != null) {
            mTextPulling = REFRESH_HEADER_PULLING;
        } else {
            mTextPulling = context.getString(com.scwang.smartrefresh.layout.R.string.srl_header_pulling);
        }
        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextLoading)) {
            mTextLoading = ta.getString(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextLoading);
        } else if (REFRESH_HEADER_LOADING != null) {
            mTextLoading = REFRESH_HEADER_LOADING;
        } else {
            mTextLoading = context.getString(com.scwang.smartrefresh.layout.R.string.srl_header_loading);
        }
        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextRelease)) {
            mTextRelease = ta.getString(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextRelease);
        } else if (REFRESH_HEADER_RELEASE != null) {
            mTextRelease = REFRESH_HEADER_RELEASE;
        } else {
            mTextRelease = context.getString(com.scwang.smartrefresh.layout.R.string.srl_header_release);
        }
        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextFinish)) {
            mTextFinish = ta.getString(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextFinish);
        } else if (REFRESH_HEADER_FINISH != null) {
            mTextFinish = REFRESH_HEADER_FINISH;
        } else {
            mTextFinish = context.getString(com.scwang.smartrefresh.layout.R.string.srl_header_finish);
        }
        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextFailed)) {
            mTextFailed = ta.getString(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextFailed);
        } else if (REFRESH_HEADER_FAILED != null) {
            mTextFailed = REFRESH_HEADER_FAILED;
        } else {
            mTextFailed = context.getString(com.scwang.smartrefresh.layout.R.string.srl_header_failed);
        }
        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextSecondary)) {
            mTextSecondary = ta.getString(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextSecondary);
        } else if (REFRESH_HEADER_SECONDARY != null) {
            mTextSecondary = REFRESH_HEADER_SECONDARY;
        } else {
            mTextSecondary = context.getString(com.scwang.smartrefresh.layout.R.string.srl_header_secondary);
        }
        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextRefreshing)) {
            mTextRefreshing = ta.getString(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextRefreshing);
        } else if (REFRESH_HEADER_REFRESHING != null) {
            mTextRefreshing = REFRESH_HEADER_REFRESHING;
        } else {
            mTextRefreshing = context.getString(com.scwang.smartrefresh.layout.R.string.srl_header_refreshing);
        }
        if (ta.hasValue(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextUpdate)) {
            mTextUpdate = ta.getString(com.scwang.smartrefresh.layout.R.styleable.ClassicsHeader_srlTextUpdate);
        } else if (REFRESH_HEADER_UPDATE != null) {
            mTextUpdate = REFRESH_HEADER_UPDATE;
        } else {
            mTextUpdate = context.getString(com.scwang.smartrefresh.layout.R.string.srl_header_update);
        }
        mLastUpdateFormat = new SimpleDateFormat(mTextUpdate, Locale.getDefault());

        ta.recycle();

//        updateView.setId(ID_TEXT_UPDATE);
        progressView.animate().setInterpolator(new LinearInterpolator());
        updateView.setVisibility(mEnableLastTime ? VISIBLE : GONE);
//        centerLayout.addView(updateView, lpUpdateText);
//        mTitleText.setText(thisView.isInEditMode() ? AppConfigManager.getAppConfig().randomDropDownText().getTextRelease() : mTextPulling);

        if (thisView.isInEditMode()) {
            arrowView.setVisibility(GONE);
        } else {
            progressView.setVisibility(GONE);
        }

        try {//try 不能删除-否则会出现兼容性问题
            if (context instanceof FragmentActivity) {
                FragmentManager manager = ((FragmentActivity) context).getSupportFragmentManager();
                if (manager != null) {
                    @SuppressLint("RestrictedApi")
                    List<Fragment> fragments = manager.getFragments();
                    if (fragments.size() > 0) {
                        setLastUpdateTime(new Date());
                        return;
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        KEY_LAST_UPDATE_TIME += context.getClass().getName();
        mShared = context.getSharedPreferences("ClassicsHeader", Context.MODE_PRIVATE);
        setLastUpdateTime(new Date(mShared.getLong(KEY_LAST_UPDATE_TIME, System.currentTimeMillis())));

    }

//    @Override
//    protected ClassicsHeader self() {
//        return this;
//    }

    //</editor-fold>

    //<editor-fold desc="RefreshHeader">

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        if (success) {
            mTitleText.setText(mTextFinish);
            if (mLastTime != null) {
                setLastUpdateTime(new Date());
            }
        } else {
            mTitleText.setText(mTextFailed);
        }
        return super.onFinish(layout, success);//延迟500毫秒之后再弹回
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        final View arrowView = mArrowView;
        final View updateView = mLastUpdateText;
        switch (newState) {
            case None:
                updateView.setVisibility(mEnableLastTime ? VISIBLE : GONE);
            case PullDownToRefresh:
                mTitleText.setText(mTextPulling);
//                mTitleText.setText(AppConfigManager.getAppConfig().randomDropDownText().getTextPulling());
                arrowView.setVisibility(VISIBLE);
                arrowView.animate().rotation(0);
                break;
            case Refreshing:
            case RefreshReleased:
                mTitleText.setText(mTextRefreshing);
//                mTitleText.setText(AppConfigManager.getAppConfig().randomDropDownText().getTextOptioning());
                arrowView.setVisibility(GONE);
                break;
            case ReleaseToRefresh:
                mTitleText.setText(mTextRelease);
//                mTitleText.setText(AppConfigManager.getAppConfig().randomDropDownText().getTextRelease());
                arrowView.animate().rotation(180);
                break;
            case ReleaseToTwoLevel:
                mTitleText.setText(mTextSecondary);
                arrowView.animate().rotation(0);
                break;
            case Loading:
                arrowView.setVisibility(GONE);
                updateView.setVisibility(mEnableLastTime ? INVISIBLE : GONE);
                mTitleText.setText(mTextLoading);
                break;
        }
    }
    //</editor-fold>

    //<editor-fold desc="API">

    public MyClassicsHeader setLastUpdateTime(Date time) {
        final View thisView = this;
        mLastTime = time;
        mLastUpdateText.setText(mLastUpdateFormat.format(time));
        if (mShared != null && !thisView.isInEditMode()) {
            mShared.edit().putLong(KEY_LAST_UPDATE_TIME, time.getTime()).apply();
        }
        return this;
    }

    public MyClassicsHeader setTimeFormat(DateFormat format) {
        mLastUpdateFormat = format;
        if (mLastTime != null) {
            mLastUpdateText.setText(mLastUpdateFormat.format(mLastTime));
        }
        return this;
    }

    public MyClassicsHeader setLastUpdateText(CharSequence text) {
        mLastTime = null;
        mLastUpdateText.setText(text);
        return this;
    }

    public MyClassicsHeader setAccentColor(@ColorInt int accentColor) {
        mLastUpdateText.setTextColor(accentColor & 0x00ffffff | 0xcc000000);
        return super.setAccentColor(accentColor);
    }

    public MyClassicsHeader setEnableLastTime(boolean enable) {
        final View updateView = mLastUpdateText;
        mEnableLastTime = enable;
        updateView.setVisibility(enable ? VISIBLE : GONE);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public MyClassicsHeader setTextSizeTime(float size) {
        mLastUpdateText.setTextSize(size);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

//    public ClassicsHeader setTextSizeTime(int unit, float size) {
//        mLastUpdateText.setTextSize(unit, size);
//        if (mRefreshKernel != null) {
//            mRefreshKernel.requestRemeasureHeightForHeader();
//        }
//        return this;
//    }

    public MyClassicsHeader setTextTimeMarginTop(float dp) {
        final View updateView = mLastUpdateText;
        MarginLayoutParams lp = (MarginLayoutParams) updateView.getLayoutParams();
        lp.topMargin = DensityUtil.dp2px(dp);
        updateView.setLayoutParams(lp);
        return this;
    }

//    public ClassicsHeader setTextTimeMarginTopPx(int px) {
//        MarginLayoutParams lp = (MarginLayoutParams)mLastUpdateText.getLayoutParams();
//        lp.topMargin = px;
//        mLastUpdateText.setLayoutParams(lp);
//        return this;
//    }

//    /**
//     * @deprecated 使用 findViewById(ID_TEXT_UPDATE) 代替
//     */
//    @Deprecated
//    public TextView getLastUpdateText() {
//        return mLastUpdateText;
//    }

    //</editor-fold>

}
