package com.mogsev.forservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    protected ITimeCounterService mInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        doBindService();
    }



    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            mInterface = ITimeCounterService.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            mInterface = null;
        }
    };

    void doBindService() {
        Intent intent = new Intent(getApplicationContext(), ITimeCounterService.class);
        intent.setAction(ITimeCounterService.class.getName());
        bindService(intent, mConnection, Context.BIND_IMPORTANT);
    }

}
