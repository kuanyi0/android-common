package com.yikuan.androidcommon.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.yikuan.androidcommon.AndroidCommon;

/**
 * @author yikuan
 * @date 2019/09/22
 */
public class ScreenUtils {
    private static WindowManager sWindowManager;
    private static Display sDisplay;

    static {
        sWindowManager = (WindowManager) AndroidCommon.getApp().getSystemService(Context.WINDOW_SERVICE);
        if (sWindowManager == null) {
            throw new RuntimeException("failed to get window manager.");
        }
        sDisplay = sWindowManager.getDefaultDisplay();
    }

    private ScreenUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static int getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        sDisplay.getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        sDisplay.getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int getScreenDpi() {
        DisplayMetrics metrics = new DisplayMetrics();
        sDisplay.getMetrics(metrics);
        return metrics.densityDpi;
    }
}
