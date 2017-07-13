package com.mogsev.simpleprojects.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Eugene Sikaylo on 12.07.2017.
 */

public class MainService extends Service {
    private static final String TAG = MainService.class.getSimpleName();

    private final MainServiceBinder mBinder = new MainServiceBinder();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private Disposable mTimerDisposable;

    Flowable<Long> flowable;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        startTimer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: " + intent + " / " + flags + " / " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private void startTimer() {
        flowable = Flowable.interval(1, TimeUnit.SECONDS);
        Consumer<Long> consumer = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                Log.i(TAG, "Timer: " + aLong);
            }
        };
        Disposable disposable = flowable.subscribe(consumer);
        mDisposable.add(disposable);
    }

    private void stopTimer() {
        flowable.repeat();
    }

    public class MainServiceBinder extends Binder {
        public MainService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MainService.this;
        }
    }
}
