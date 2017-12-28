package com.mogsev;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo on 12/28/2017.
 * email: mogsev@gmail.com
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("onCreate");

        // initialize Timber
        initTimber();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
