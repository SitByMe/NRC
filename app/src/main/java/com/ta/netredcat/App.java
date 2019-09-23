package com.ta.netredcat;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;

import com.blankj.utilcode.util.ActivityUtils;
import com.ta.netredcat.constant.SpConstants;
import com.ta.netredcat.ui.login.LoginActivity;
import com.ta.netredcat.ui.main.MainActivity;
import com.ta.netredcat.utils.SpUtils;
import com.ta.netredcat.utils.helper.SpHelper;
import com.ta.netredcat.utils.helper.StartActivityUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App extends Application {
    public static App mApp;
//    private static AppConfig appConfig;

//    public static void setAppConfig(AppConfig appConfig) {
//        App.appConfig = appConfig;
//    }

//    public static AppConfig getAppConfig() {
//        return appConfig;
//    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
//            layout.setPrimaryColorsId(android.R.color.transparent, R.color.home_normal_color);//全局设置主题颜色
//            return new MyClassicsHeader(context).setDrawableSize(12).setTextSizeTitle(14).setTextSizeTime(12);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));;
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
//            //指定为经典Footer，默认是 BallPulseFooter
//            return new MyClassicsFooter(context).setDrawableSize(14).setTextSizeTitle(14);
//        });
    }

    public static String PICDIR;

    public static boolean requestNotice = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        PICDIR = Environment.getExternalStorageDirectory() + "/" + App.getAppName() + "/";
//        MultiDex.install(this);
//        DFresco.init(this);
//        initJPush();
//        initShare();
//        initVideoPlayer();
//        initFileDownLoad();
//        initBugly();
    }

    private void initVideoPlayer() {
        //EXOPlayer内核，支持格式更多
//        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        //exo缓存模式，支持m3u8，只支持exo
//        CacheFactory.setCacheManager(ExoPlayerCacheManager.class);
    }

    private void initFileDownLoad() {
//        FileDownloader.enableAvoidDropFrame();
//        FileDownloader.setupOnApplicationOnCreate(this)
//                .connectionCreator(new FileDownloadUrlConnection
//                        .Creator(new FileDownloadUrlConnection.Configuration()
//                        .connectTimeout(15_000) // set connection timeout.
//                        .readTimeout(15_000) // set read timeout.
//                ))
//                .commit();
    }

    private void initJPush() {
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
    }

    private void initBugly() {
//        Context context = getApplicationContext();
//        // 获取当前包名
//        String packageName = context.getPackageName();
//        // 获取当前进程名
//        String processName = getProcessName(android.os.Process.myPid());
//        // 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        CrashReport.initCrashReport(getApplicationContext(), "0310427599", BuildConfig.SHOW_BUGLY_LOG);
    }

    private void initShare() {
//        FUtils.init(this);
    }

    public static Context getContext() {
        return mApp.getApplicationContext();
    }

    public static String getAppName() {
        return getContext().getResources().getString(R.string.app_name);
    }

    public boolean hasLogin(boolean toLogin) {
        if (TextUtils.isEmpty(SpUtils.getString(SpConstants.USER_TOKEN))) {
            if (toLogin) {
                ActivityUtils.startActivity(new Intent(this, LoginActivity.class));
            }
            return false;
        } else {
            return true;
        }
    }

    public boolean hasLogin() {
        return hasLogin(false);
    }

    public void logOut() {
        clearLocalConfig();
        StartActivityUtils.startActivity(MainActivity.class);
    }

    private void clearLocalConfig() {
        SpHelper.INSTANCE.removeUserInfo();
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
