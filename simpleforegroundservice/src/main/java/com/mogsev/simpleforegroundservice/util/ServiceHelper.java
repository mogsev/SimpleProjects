package com.mogsev.simpleforegroundservice.util;

import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

/**
 * Created by Eugene Sikaylo on 21.07.2017.
 */

public final class ServiceHelper {
    private static final String TAG = ServiceHelper.class.getSimpleName();

    private ServiceHelper() {
        // empty
    }

    public static boolean isServiceRunning(@NonNull Context context, Class<?> clazz) {
        Log.i(TAG, "isServiceRunning");
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        boolean result = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> listServices = am.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : listServices) {
            if (runningServiceInfo.service.getClassName().equals(clazz.getName())) {
                result = true;
            }
        }

        return result;
    }

    public static boolean isServiceRunningForeground(@NonNull Context context, Class<?> clazz) {
        Log.i(TAG, "isServiceRunningForeground");
        boolean result = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> listServices = am.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : listServices) {
            //Log.i(TAG, "Service className: " + runningServiceInfo.service.getClassName());
            if (runningServiceInfo.service.getClassName().equals(clazz.getName())) {
                if (runningServiceInfo.foreground) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

}
