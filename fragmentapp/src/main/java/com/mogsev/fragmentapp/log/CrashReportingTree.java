package com.mogsev.fragmentapp.log;

import android.util.Log;


import timber.log.Timber;

/**
 * Created by Eugene Sikaylo on 10/10/2017.
 * mogsev@gmail.com
 */

public class CrashReportingTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }


    }
}
