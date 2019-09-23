package com.ta.netredcat.ui.mvp;

import android.content.Context;

import com.ta.netredcat.data.db.DBHelperImpl;
import com.ta.netredcat.data.net.ApiHelperImpl;
import com.ta.netredcat.data.sp.SpHelperImpl;

public class AppDataManager implements DataManagerImpl {

    private Context mContext;

    private DBHelperImpl mDbHelper;
    private ApiHelperImpl mApiHelper;
    private SpHelperImpl mSpHelper;

    public AppDataManager(Context mContext, DBHelperImpl mDbHelper, ApiHelperImpl mApiHelper, SpHelperImpl mSpHelper) {
        this.mContext = mContext;
        this.mDbHelper = mDbHelper;
        this.mApiHelper = mApiHelper;
        this.mSpHelper = mSpHelper;
    }

    public DBHelperImpl getDbHelper() {
        return mDbHelper;
    }

    public ApiHelperImpl getApiHelper() {
        return mApiHelper;
    }

    public SpHelperImpl getSpHelper() {
        return mSpHelper;
    }

    public <T extends DBHelperImpl> T getDbHelper(Class<T> cls) {
        return (T) mDbHelper;
    }

    public <T extends ApiHelperImpl> T getApiHelper(Class<T> cls) {
        return (T) mApiHelper;
    }

    public <T extends SpHelperImpl> T getSpHelper(Class<T> cls) {
        return (T) mSpHelper;
    }
}
