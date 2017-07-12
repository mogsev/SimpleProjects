package com.mogsev.basecontent;

import android.app.Application;
import android.util.Log;

import com.mogsev.basecontent.utils.MainHelper;

/**
 * Created by Eugene Sikaylo on 12.07.2017.
 */

public abstract class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        // show resource layout type
        if (BuildConfig.DEBUG) MainHelper.showResourceLayoutType(this);
    }
}
