package com.yikuan.androidcommon.util;

import android.util.DisplayMetrics;
import com.yikuan.androidcommon.AndroidCommon;

/**
 * @author yikuan
 * @date 2019/09/13
 */
public class DisplayUtils {
    private static DisplayMetrics sDisplayMetrics;

    static {
        sDisplayMetrics = AndroidCommon.getApp().getResources().getDisplayMetrics();
    }

    private DisplayUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static int px2dp(float px) {
        float scale = sDisplayMetrics.density;
        return (int) (px / scale + 0.5);
    }

    public static int dp2px(float dp) {
        float scale = sDisplayMetrics.density;
        return (int) (dp * scale + 0.5);
    }

    public static int px2sp(float px) {
        float fontScale = sDisplayMetrics.scaledDensity;
        return (int) (px / fontScale + 0.5);
    }

    public static int sp2px(float sp) {
        float fontScale = sDisplayMetrics.scaledDensity;
        return (int) (sp * fontScale + 0.5);
    }
}
