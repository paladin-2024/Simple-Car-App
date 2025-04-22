package com.example.task1_activitylifecyclemethods.Utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

public class PermissionUtils {
    public static final int REQUEST_STORAGE_PERMISSION = 1001;

    public static boolean checkReadStoragePermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Check if we should show permission rationale
                if (activity.shouldShowRequestPermissionRationale(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Explain why permission is needed
                }

                activity.requestPermissions(
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_STORAGE_PERMISSION
                );
                return false;
            }
            return true;
        }
        // Permission automatically granted on SDK < 23
        return true;
    }
}