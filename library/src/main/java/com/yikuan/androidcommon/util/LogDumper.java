package com.yikuan.androidcommon.util;

import android.os.Process;

import java.io.File;

/**
 * @author yikuan
 * @date 2020/08/04
 */
public class LogDumper {
    private static final String TAG = "LogDumper";
    private static final String DIR = "dumper";
    private static final String FILE_NAME_SUFFIX = ".log";
    private java.lang.Process mDumpProcess;

    private static class Instance {
        private static final LogDumper INSTANCE = new LogDumper();
    }

    private LogDumper() {
    }

    public static LogDumper getInstance() {
        return Instance.INSTANCE;
    }

    public void start() {
        String externalAppFilesPath = PathUtils.getExternalAppFilesPath();
        if (externalAppFilesPath.isEmpty()) {
            LogUtils.d(TAG, "external storage unmounted");
            return;
        }
        File dir = FileUtils.getFile(externalAppFilesPath, DIR);
        try {
            FileUtils.forceMkdir(dir);
            String fileName = DateUtils.formatTimeFileName() + FILE_NAME_SUFFIX;
            File file = FileUtils.getFile(dir, fileName);
            if (!file.exists() && !file.createNewFile()) {
                LogUtils.d(TAG, "fail to create log dumper file");
                return;
            }
            String path = file.getAbsolutePath();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("logcat -c");
            mDumpProcess = runtime.exec("logcat -f " + path + " --pid=" + Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
            destroyPrecess();
        }
    }

    public void stop() {
        destroyPrecess();
    }

    private void destroyPrecess() {
        if (mDumpProcess != null) {
            mDumpProcess.destroy();
        }
    }
}
