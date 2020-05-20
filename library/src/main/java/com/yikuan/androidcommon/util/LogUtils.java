package com.yikuan.androidcommon.util;

import android.os.Process;
import android.util.Log;

import androidx.annotation.IntDef;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yikuan
 * @date 2019/09/22
 */
public class LogUtils {
    private static final String TAG = "LogUtils";
    private static final String DIR = "log";
    private static java.lang.Process sProcess;

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

    public static void startDump2File() {
        String externalAppFilesPath = PathUtils.getExternalAppFilesPath();
        if (externalAppFilesPath.isEmpty()) {
            LogUtils.d(TAG, "external storage unmounted");
            return;
        }
        File dir = FileUtils.getFile(externalAppFilesPath, DIR);
        try {
            FileUtils.forceMkdir(dir);
            String fileName = TimeUtils.formatFileName();
            File file = FileUtils.getFile(dir, fileName);
            if (!file.exists() && !file.createNewFile()) {
                LogUtils.d(TAG, "fail to create log file");
                return;
            }
            String path = file.getAbsolutePath();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("logcat -c");
            sProcess = runtime.exec("logcat -f " + path + " --pid=" + Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopDump2File() {
        sProcess.destroy();
    }
}
