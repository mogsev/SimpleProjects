package com.mogsev.simpleprojects.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Eugene Sikaylo on 12.07.2017.
 */

public class MainService extends Service {
    private static final String TAG = MainService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
