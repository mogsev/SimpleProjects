package com.mogsev.forservice;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.mogsev.forservice.service.TimeCounterService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        // initialize realm
        initRealm();

        startService(new Intent(this, TimeCounterService.class));
    }

    private void initRealm() {
        Log.i(TAG, "initRealm");
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
