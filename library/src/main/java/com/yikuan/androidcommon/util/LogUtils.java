package com.yikuan.androidcommon.util;

import androidx.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yikuan
 * @date 2019/09/22
 */
public class LogUtils {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int ASSERT = 6;
    public static final int NOTHING = 7;

    @IntDef({VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT, NOTHING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Level {

    }

    @Level
    private static int sLevel = VERBOSE;

    private LogUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static void setLevel(@Level int level) {
        sLevel = level;
    }

    public static void v(String tag, String msg) {
        if (sLevel <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sLevel <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sLevel <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sLevel <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sLevel <= ERROR) {
            Log.e(tag, msg);
        }
    }

    public static void wtf(String tag, String msg) {
        if (sLevel <= ASSERT) {
            Log.wtf(tag, msg);
        }
    }


}
