package com.mogsev.screenblurexample;

import android.app.Application;
import android.support.v4.app.FragmentManager;

import com.mogsev.basecontent.logs.CrashReportingTree;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("");

        // initialize Timber
        initTimber();

        // enable debug of fragment manager
        FragmentManager.enableDebugLogging(BuildConfig.DEBUG);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
