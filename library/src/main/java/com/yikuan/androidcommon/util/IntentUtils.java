package com.yikuan.androidcommon.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * @author yikuan
 * @date 2020/09/27
 */
public class IntentUtils {

    private IntentUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static void selectFile(Activity activity, String dirPath) {
        if (dirPath.isEmpty()) {
            return;
        }
        File dir = new File(dirPath);
        selectFile(activity, dir);
    }

    public static void selectFile(Activity activity, File dir) {
        if (dir == null) {
            return;
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".androidcommon.fileprovider", dir);
        } else {
            uri = Uri.fromFile(dir);
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setDataAndType(uri, "*/*");
        try {
            activity.startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}