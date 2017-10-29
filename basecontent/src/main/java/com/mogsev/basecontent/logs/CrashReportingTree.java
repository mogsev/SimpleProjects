package com.mogsev.basecontent.logs;

import android.util.Log;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class CrashReportingTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {

        switch (priority) {
            case Log.VERBOSE:
                return;
            case Log.DEBUG:
                return;
            case Log.INFO:
                return;
        }

    }

}
