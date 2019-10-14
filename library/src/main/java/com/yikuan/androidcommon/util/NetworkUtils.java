package com.yikuan.androidcommon.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yikuan.androidcommon.AndroidCommon;

/**
 * @author yikuan
 * @date 2019/10/14
 */
public class NetworkUtils {
    private static ConnectivityManager sConnectivityManager;

    static {
        sConnectivityManager = (ConnectivityManager) AndroidCommon.getApp()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static boolean isConnected() {
        NetworkInfo networkInfo = sConnectivityManager.getActiveNetworkInfo();
        checkNetworkInfo(networkInfo);
        return networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public static boolean isWifiConnected() {
        NetworkInfo networkInfo = sConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        checkNetworkInfo(networkInfo);
        return networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public static boolean isMobileConnected() {
        NetworkInfo networkInfo = sConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        checkNetworkInfo(networkInfo);
        return networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public static int getConnectedType() {
        NetworkInfo networkInfo = sConnectivityManager.getActiveNetworkInfo();
        checkNetworkInfo(networkInfo);
        return networkInfo.getType();
    }

    private static void checkNetworkInfo(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            throw new RuntimeException("failed to get network info.");
        }
    }
}
