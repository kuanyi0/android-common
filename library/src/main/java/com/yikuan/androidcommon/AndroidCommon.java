package com.yikuan.androidcommon;

import android.app.Application;
import android.content.Context;

/**
 * @author yikuan
 * @date 2019/09/08
 */
public class AndroidCommon {
    private static Application sApplication;

    private AndroidCommon() {
        throw new UnsupportedOperationException("cannot instantiate.");
    }

    public static void init(Context context) {
        if (context == null) {
            throw new NullPointerException("context is null.");
        }
        sApplication = (Application) context.getApplicationContext();
    }

    public static Application getApp() {
        return sApplication;
    }
}
