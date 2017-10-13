package com.mogsev.fragmentapp;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.mogsev.fragmentapp.log.CrashReportingTree;
import com.orhanobut.hawk.Hawk;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

/**
 * Created by Eugene Sikaylo on 9/25/2017.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("onCreate");

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

        Fabric.with(this, new Crashlytics());

        // initialize Timber
        initTimber();

        // initialize Hawk
        initHawk();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    private void initHawk() {
        Hawk.init(this).build();
    }

}
