package com.yikuan.androidcommon.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.RequiresPermission;

import com.yikuan.androidcommon.AndroidCommon;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

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
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean isConnected() {
        NetworkInfo networkInfo = sConnectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean isWifiConnected() {
        NetworkInfo networkInfo = sConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean isMobileConnected() {
        NetworkInfo networkInfo = sConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static int getConnectedType() {
        NetworkInfo networkInfo = sConnectivityManager.getActiveNetworkInfo();
        return networkInfo == null ? -1 : networkInfo.getType();
    }
}
