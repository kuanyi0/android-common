package com.yikuan.androidcommon.util;

import android.os.Process;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author yikuan
 * @date 2020/04/03
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static final String DIR = "crash";
    private static final String FILE_NAME_SUFFIX = ".log";
    private static final String PATTERN_LOG_TIME = "MM-dd HH:mm:ss";
    private static final String LOG_TITLE = "Crash at %s";
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private static class Instance {
        private static final CrashHandler INSTANCE = new CrashHandler();
    }

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
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
            LogUtils.d(TAG, "external storage unmounted");
            return;
        }
        File dir = FileUtils.getFile(path, DIR);
        FileUtils.forceMkdir(dir);
        String fileName = DateUtils.formatDateFileName() + FILE_NAME_SUFFIX;
        File file = FileUtils.getFile(dir, fileName);
        String time = DateUtils.format(new Date(), PATTERN_LOG_TIME);
        String title = String.format(LOG_TITLE + "\n", time);
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        fileOutputStream.write(title.getBytes());
        PrintWriter pw = new PrintWriter(fileOutputStream);
        e.printStackTrace(pw);
        pw.println();
        pw.close();
        fileOutputStream.close();
    }

    private String getStackTrace(Throwable e) {
        StackTraceElement[] traceElements = e.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e).append("\n");
        for (StackTraceElement traceElement : traceElements) {
            stringBuilder.append("\tat ");
            stringBuilder.append(traceElement.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
