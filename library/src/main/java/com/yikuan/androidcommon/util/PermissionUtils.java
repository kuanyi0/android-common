package com.yikuan.androidcommon.util;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.yikuan.androidcommon.AndroidCommon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @author yikuan
 * @date 2019/10/13
 */
public class PermissionUtils {
    private static Application sContext;

    static {
        sContext = AndroidCommon.getApp();
    }

    private PermissionUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static boolean isGranted(String permission) {
        return ContextCompat.checkSelfPermission(sContext, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isGranted(String... permissions) {
        for (String permission : permissions) {
            if (!isGranted(permission)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDrawOverlaysGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(sContext);
        } else {
            return false;
        }
    }

    public static boolean isWriteSettingsGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.System.canWrite(sContext);
        } else {
            return false;
        }
    }

    public static void requestDrawOverlays() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + sContext.getPackageName()));
            sContext.startActivity(intent);
        }
    }

    public static void requestWriteSettings() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + sContext.getPackageName()));
            sContext.startActivity(intent);
        }
    }

    /**
     * Just to example
     */
    private static class PermissionActivity extends AppCompatActivity {
        private static final int REQUEST_CODE = 0;
        private String permission = null;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    // Show an explanation to user, don't block this thread, after explanation,
                    // try again to request the permission.
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE);
                }
            } else {
                // Permission has already been granted.
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case REQUEST_CODE:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // Permission was granted.
                    } else {
                        // Permission was denied.
                    }
                    break;
                default:
                    break;
            }

        }
    }
}
