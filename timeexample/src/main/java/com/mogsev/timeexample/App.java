package com.mogsev.timeexample;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("onCreate");

        // initialize Timber
        initTimber();

        JodaTimeAndroid.init(this);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
