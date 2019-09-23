package com.ta.netredcat.ui.login

import android.content.Context

import com.ta.netredcat.ui.mvp.AppDataManager

class LoginDataManager(mContext: Context) : AppDataManager(mContext, null, null, null)



/* void login(String username, String password, String area, Consumer<Disposable> before, ObserverOnNextListener<LoginResult> listener,Context context) {
        ((LoginApiHelper) getApiHelper()).login(username, password, area, before, new AppObserver<>(listener),context);
    }

    static class LoginApiHelper extends AppApiHelper {
        public void login(String username, String password, String area, Consumer<Disposable> before, Observer<AppResult<LoginResult>> observer,Context context) {
            UserHttpMethods.login(username, password, area, before, observer,context);
        }
    }*/
