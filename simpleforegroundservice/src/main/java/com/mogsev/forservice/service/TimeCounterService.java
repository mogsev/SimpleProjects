package com.mogsev.forservice.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.mogsev.forservice.ITimeCounterService;
import com.mogsev.forservice.R;
import com.mogsev.forservice.aidlmodel.Punch;
import com.mogsev.forservice.viewmodel.TimeCounterViewModel;


public class TimeCounterService extends Service {
    private static final String TAG = TimeCounterService.class.getSimpleName();

    private final int NOTIFICATION_ID = 10001;

    private Notification mNotification;

    private TimeCounterViewModel mViewModel;

    public TimeCounterService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        startForeground();

        mViewModel.startPunchIn();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: " + intent.toString() + " / " + flags + " / " + startId);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private void startForeground() {
        Log.i(TAG, "startForeground");
        showNotification();
    }

    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = getText(R.string.app_name);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setTicker(text)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("Service")  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .build();
        startForeground(NOTIFICATION_ID, notification);

    }

    private final ITimeCounterService.Stub mBinder = new ITimeCounterService.Stub() {

        public long getMillis() {
            return 1001;
        }

        public Punch getPunch() {
            return new Punch();
        }

    };

}
