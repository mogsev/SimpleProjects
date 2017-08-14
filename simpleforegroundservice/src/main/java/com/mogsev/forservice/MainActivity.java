package com.mogsev.forservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mogsev.forservice.databinding.ActivityMainBinding;
import com.mogsev.forservice.service.TimeCounterService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mBinding;

    protected ITimeCounterService mInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        doBindService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        if (mConnection != null && mInterface != null) {
            unbindService(mConnection);
        }
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick: " + v);
        switch (v.getId()) {
            case R.id.button_start_service:
                try {
                    doBindService();
                    if (mInterface != null) {
                        mInterface.start();
                    } else {
                        Log.e(TAG, "mInterface is null");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_stop_service:
                try {
                    if (mInterface != null) {
                        mInterface.stop();
                    } else {
                        Log.e(TAG, "mInterface is null");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_do_action:
                try {
                    if (mInterface != null) {
                        mBinding.textMillis.setText(String.valueOf(mInterface.getMillis()));
                        Log.i(TAG, "Punch: " + mInterface.getPunch());
                    } else {
                        Log.e(TAG, "mInterface is null");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void initView() {
        Log.i(TAG, "initView");
        mBinding.buttonStartService.setOnClickListener(this);
        mBinding.buttonStopService.setOnClickListener(this);
        mBinding.buttonDoAction.setOnClickListener(this);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.i(TAG, "onServiceConnected: " + className + "/ " + service);
            mInterface = ITimeCounterService.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.i(TAG, "onServiceDisconnected: " + className);
            mInterface = null;
        }
    };

    void doBindService() {
        Log.i(TAG, "doBindService");
        Intent intent = new Intent(getApplicationContext(), TimeCounterService.class);
        intent.setAction(ITimeCounterService.class.getName());
        bindService(intent, mConnection, Context.BIND_IMPORTANT);
    }

}
