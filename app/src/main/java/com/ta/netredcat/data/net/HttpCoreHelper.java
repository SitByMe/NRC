package com.ta.netredcat.data.net;

import android.util.Log;

import com.ta.netredcat.App;
import com.ta.netredcat.constant.SpConstants;
import com.ta.netredcat.utils.SpUtils;
import com.ta.netredcat.utils.helper.SpHelper;
import com.tianao.module.net.http.HttpCore;

public class HttpCoreHelper extends HttpCore {
    public static <S> S getService(Class<S> iServer) {
        Log.e("nrc", "token==" + SpUtils.getString(SpConstants.USER_TOKEN) + ",uid==" + SpUtils.getInt(SpConstants.UID) + "");
        String apiHost = SpHelper.INSTANCE.getApiHost();
        return HttpCore.getService(iServer, apiHost, App.mApp.hasLogin(), SpUtils.getString(SpConstants.USER_TOKEN), SpUtils.getInt(SpConstants.UID) + "");
    }

    public static <S> S getService(Class<S> iServer, boolean resultIsObject) {
        Log.e("nrc", "token==" + SpUtils.getString(SpConstants.USER_TOKEN) + ",uid==" + SpUtils.getInt(SpConstants.UID) + "");
        String apiHost = SpHelper.INSTANCE.getApiHost();
        return HttpCore.getService(iServer, apiHost, App.mApp.hasLogin(), SpUtils.getString(SpConstants.USER_TOKEN), SpUtils.getInt(SpConstants.UID) + "", resultIsObject);
    }
}
