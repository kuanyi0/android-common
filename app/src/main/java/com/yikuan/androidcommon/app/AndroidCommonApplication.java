package com.yikuan.androidcommon.app;

import android.app.Application;

import com.yikuan.androidcommon.AndroidCommon;

/**
 * @author yikuan
 * @date 2020/01/10
 */
public class AndroidCommonApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidCommon.init(this);
    }
}
