package com.yikuan.androidcommon.util;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;

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


    public class PermissionActivity extends AppCompatActivity {
        private static final int REQUEST_CODE = 0;
        private String permission = null;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                // todo: show an explanation to user, don't block this thread,
                //  after explanation, try again to request the permission.
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE);
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case REQUEST_CODE:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // todo: permission was granted.
                    } else {
                        // todo: permission was denied.
                    }
                    break;
                default:
                    break;
            }

        }
    }
}
