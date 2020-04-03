package com.yikuan.androidcommon.util;

import android.os.Process;
import androidx.annotation.NonNull;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yikuan
 * @date 2020/04/03
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;
    private static final String DIR = "/crash/log";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static class Instance {
        private static final CrashHandler INSTANCE = new CrashHandler();
    }

    private CrashHandler() {

    }

    public CrashHandler getInstance() {
        return Instance.INSTANCE;
    }

    public void init() {
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        try {
            dumpException2File(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
        if (mDefaultExceptionHandler != null) {
            mDefaultExceptionHandler.uncaughtException(t, e);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    private void dumpException2File(Throwable e) throws IOException {
        String path = PathUtils.getExternalAppFilesPath();
        if (path.isEmpty()) {
            LogUtils.d(TAG,"external storage unmounted");
            return;
        }
        File dir = FileUtils.getFile(path, DIR);
        FileUtils.forceMkdir(dir);
        String fileName = FILE_NAME + TimeUtils.formatFileName() + FILE_NAME_SUFFIX;
        File file = FileUtils.getFile(dir, fileName);
        PrintWriter pw = new PrintWriter(file);
        e.printStackTrace(pw);
        pw.close();
    }
}
