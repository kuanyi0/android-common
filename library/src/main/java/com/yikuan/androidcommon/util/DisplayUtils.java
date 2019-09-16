package com.yikuan.androidcommon.util;

import com.yikuan.androidcommon.AndroidCommon;

/**
 * @author yikuan
 * @date 2019/09/13
 */
public class DisplayUtils {

    private DisplayUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static int px2dp(float px) {
        float scale = AndroidCommon.getApp().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5);
    }

    public static int dp2px(float dp) {
        float scale = AndroidCommon.getApp().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5);
    }

    public static int px2sp(float px) {
        float fontScale = AndroidCommon.getApp().getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5);
    }

    public static int sp2px(float sp) {
        float fontScale = AndroidCommon.getApp().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5);
    }
}
