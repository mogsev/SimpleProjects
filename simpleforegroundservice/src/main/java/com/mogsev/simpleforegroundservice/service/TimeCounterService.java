package com.mogsev.simpleforegroundservice.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import com.mogsev.simpleforegroundservice.MainActivity;
import com.mogsev.simpleforegroundservice.R;
import com.mogsev.simpleforegroundservice.util.ServiceHelper;
import com.mogsev.simpleforegroundservice.viewmodel.TimeCounterViewModel;

import java.util.Date;

public class TimeCounterService extends Service {
    private static final String TAG = TimeCounterService.class.getSimpleName();

    private final int NOTIFICATION_ID = 10001;

    private NotificationManager mNotificationManager;

    private TimeCounterViewModel mTimeCounterViewModel = new TimeCounterViewModel();

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    final IBinder mBinder = new LocalBinder();

    public TimeCounterService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mTimeCounterViewModel.setTextPunchInBefore(getString(R.string.text_punched_in_before_args));
        mTimeCounterViewModel.setTextPunchOutBefore(getString(R.string.text_punched_out_before_args));
        startForeground();

        mTimeCounterViewModel.startPunchIn();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: " + intent.toString() + " / " + flags + " / " + startId);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private void startForeground() {
        Log.i(TAG, "startForeground");
        if (!ServiceHelper.isServiceRunningForeground(getApplicationContext(), TimeCounterService.class)) {
            showNotification();
        } else {
            Log.i(TAG, "Service is already running foreground");
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "onRebind");
    }

    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = getText(R.string.app_name);

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setTicker(text)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("Service")  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .build();
        startForeground(NOTIFICATION_ID, notification);

        // Send the notification.
        //mNM.notify(NOTIFICATION, notification);

    }

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {

        public TimeCounterService getService() {
            return TimeCounterService.this;
        }

    }
}
