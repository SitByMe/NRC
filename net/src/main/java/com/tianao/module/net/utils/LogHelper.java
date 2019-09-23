package com.tianao.module.net.utils;

import com.facebook.stetho.common.LogUtil;

public class LogHelper {
    /**
     * 分段打印出较长log文本
     *
     * @param logContent 打印文本
     * @param showLength 规定每段显示的长度（AndroidStudio控制台打印log的最大信息量大小为4k）
     * @param tag        打印log的标记
     */
    public static void showLargeLog(String logContent, int showLength, String tag) {
        if (logContent.length() > showLength) {
            String show = logContent.substring(0, showLength);
            LogUtil.i(show);
            /*剩余的字符串如果大于规定显示的长度，截取剩余字符串进行递归，否则打印结果*/
            if ((logContent.length() - showLength) > showLength) {
                String partLog = logContent.substring(showLength, logContent.length());
                showLargeLog(partLog, showLength, tag);
            } else {
                String printLog = logContent.substring(showLength, logContent.length());
                LogUtil.i(printLog);
            }

        } else {
            LogUtil.i(logContent);
        }
    }
}
