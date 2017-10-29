package com.mogsev.simpleprojects;

import android.support.v4.app.FragmentManager;

import com.mogsev.basecontent.BaseApplication;
import com.mogsev.basecontent.logs.CrashReportingTree;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo on 12.07.2017.
 */

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("onCreate");

        // initialize Timber
        initTimber();

        // enable debug of fragment manager
        FragmentManager.enableDebugLogging(BuildConfig.DEBUG);

        // start service
        //startService(new Intent(this, TimeCounterService.class));

        //startService(new Intent(this, MainService.class));
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

}
