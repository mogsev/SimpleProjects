package com.mogsev.fragmentapp;

import android.app.Application;

import com.mogsev.fragmentapp.utils.Logger;

/**
 * Created by Eugene Sikaylo on 9/25/2017.
 */

public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i(TAG, "onCreate");
    }
}
