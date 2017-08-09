package com.mogsev.simpleforegroundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mogsev.simpleforegroundservice.service.TimeCounterService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        doBindService();
    }


    private TimeCounterService mService;

    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            TimeCounterService.LocalBinder binder = (TimeCounterService.LocalBinder) service;
            MainActivity.this.mService = (TimeCounterService) binder.getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            // Because it is running in our same process, we should never
            // see this happen.
            mService = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this,
                TimeCounterService.class), mConnection, 0);
    }

}
