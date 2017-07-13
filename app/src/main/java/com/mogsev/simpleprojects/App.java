package com.mogsev.simpleprojects;

import android.content.Intent;
import android.util.Log;

import com.mogsev.basecontent.BaseApplication;
import com.mogsev.simpleprojects.service.MainService;
import com.mogsev.simpleprojects.service.TimeCounterService;

/**
 * Created by Eugene Sikaylo on 12.07.2017.
 */

public class App extends BaseApplication {
    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        // start service
        startService(new Intent(this, TimeCounterService.class));

        startService(new Intent(this, MainService.class));
    }

}
