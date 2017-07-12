package com.mogsev.simpleprojects.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.mogsev.simpleprojects.R;
import com.mogsev.simpleprojects.viewmodel.TimeCounterViewModel;


/**
 * Created by Eugene Sikaylo on 22.06.2017.
 */

public class TimeCounterService extends Service {
    private static final String TAG = TimeCounterService.class.getSimpleName();

    private static final int NOTIFICATION = 147852369;

    private final TimeCounterBinder mBinder = new TimeCounterBinder();

    private TimeCounterViewModel mTimeCounterViewModel = new TimeCounterViewModel();

    private NotificationCompat.Builder mNotificationBuilder;
    private Notification mNotification;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        createNotification();

        startForeground(NOTIFICATION, mNotification);
        mTimeCounterViewModel.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        mTimeCounterViewModel.stop();
        stopForeground(true);
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public TimeCounterViewModel getTimeCounterViewModel() {
        return mTimeCounterViewModel;
    }

    public class TimeCounterBinder extends Binder {
        public TimeCounterService getService() {
            // Return this instance of LocalService so clients can call public methods
            return TimeCounterService.this;
        }
    }

    private void createNotification() {
        Log.i(TAG, "createNotification");
        mNotificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_pulse_blue_24dp)
                        .setContentTitle("Time counter")
                        .setContentText("Hello World!")
                        .setAutoCancel(false);

        mNotification = mNotificationBuilder.build();
    }
}
