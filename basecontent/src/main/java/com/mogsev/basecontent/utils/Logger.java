package com.mogsev.basecontent.utils;

import android.util.Log;

import com.mogsev.basecontent.BuildConfig;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public final class Logger {

    private Logger() {
        // empty
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable t) {
        if (BuildConfig.DEBUG) Log.i(tag, msg, t);
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable t) {
        if (BuildConfig.DEBUG) Log.d(tag, msg, t);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable t) {
        Log.e(tag, msg, t);
    }

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable t) {
        if (BuildConfig.DEBUG) Log.v(tag, msg, t);
    }

}
