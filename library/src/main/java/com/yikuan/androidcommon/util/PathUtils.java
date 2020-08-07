package com.yikuan.androidcommon.util;

import android.os.Environment;

import com.yikuan.androidcommon.AndroidCommon;

import java.io.File;

/**
 * @author yikuan
 * @date 2019/10/13
 */
public class PathUtils {

    private PathUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    //--------------------------------------------------------------------
    // Internal public storage
    //--------------------------------------------------------------------

    /**
     * @return /system
     */
    public static String getRootPath() {
        return getAbsolutePath(Environment.getRootDirectory());
    }

    /**
     * @return /data
     */
    public static String getDataPath() {
        return getAbsolutePath(Environment.getDataDirectory());
    }

    /**
     * @return /data/cache
     */
    public static String getDownloadCachePath() {
        return getAbsolutePath(Environment.getDownloadCacheDirectory());
    }

    //--------------------------------------------------------------------
    // Internal private storage
    //--------------------------------------------------------------------

    /**
     * @return /data/data/package/cache
     */
    public static String getInternalAppCachePath() {
        return getAbsolutePath(AndroidCommon.getApp().getCacheDir());
    }

    /**
     * @return /data/data/package/files
     */
    public static String getInternalAppFilesPath() {
        return getAbsolutePath(AndroidCommon.getApp().getFilesDir());
    }

    //--------------------------------------------------------------------
    // External public storage
    //--------------------------------------------------------------------

    public static String getExternalStoragePath() {
        checkExternalStorageState();
        return getAbsolutePath(Environment.getExternalStorageDirectory());
    }

    public static String getExternalPicturesPath() {
        checkExternalStorageState();
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    public static String getExternalMusicPath() {
        checkExternalStorageState();
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
    }

    public static String getExternalDcimPath() {
        checkExternalStorageState();
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
    }

    public static String getExternalDownloadsPath() {
        checkExternalStorageState();
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
    }

    public static String getExternalMoviesPath() {
        checkExternalStorageState();
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));
    }

    public static String getExternalAlarmsPath() {
        checkExternalStorageState();
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS));
    }

    public static String getExternalRingtonesPath() {
        checkExternalStorageState();
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES));
    }

    public static String getExternalPodcastsPath() {
        checkExternalStorageState();
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS));
    }

    public static String getExternalNotificationsPath() {
        checkExternalStorageState();
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS));
    }

    //--------------------------------------------------------------------
    // External private storage
    //--------------------------------------------------------------------

    public static String getExternalAppDataPath() {
        checkExternalStorageState();
        File externalCacheDir = AndroidCommon.getApp().getExternalCacheDir();
        if (externalCacheDir == null) {
            return "";
        }
        return getAbsolutePath(externalCacheDir.getParentFile());
    }

    public static String getExternalAppCachePath() {
        checkExternalStorageState();
        return getAbsolutePath(AndroidCommon.getApp().getExternalCacheDir());
    }

    public static String getExternalAppFilesPath() {
        checkExternalStorageState();
        return getAbsolutePath(AndroidCommon.getApp().getExternalFilesDir(null));
    }

    public static String getExternalAppMusicPath() {
        checkExternalStorageState();
        return getAbsolutePath(AndroidCommon.getApp().getExternalFilesDir(Environment.DIRECTORY_MUSIC));
    }

    public static String getExternalAppObbPath() {
        checkExternalStorageState();
        return getAbsolutePath(AndroidCommon.getApp().getObbDir());
    }


    private static String getAbsolutePath(File file) {
        if (file == null) {
            return "";
        }
        return file.getAbsolutePath();
    }

    private static void checkExternalStorageState() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            throw new RuntimeException("external storage isn't mounted.");
        }
    }
}
