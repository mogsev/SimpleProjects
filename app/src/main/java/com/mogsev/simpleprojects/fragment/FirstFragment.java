package com.mogsev.simpleprojects.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.simpleprojects.R;
import com.mogsev.simpleprojects.databinding.FragmentFirstBinding;
import com.mogsev.simpleprojects.service.TimeCounterService;

public class FirstFragment extends Fragment implements ServiceConnection {
    private static final String TAG = FirstFragment.class.getSimpleName();

    private TimeCounterService mTimeCounterService;

    private FragmentFirstBinding mBinding;

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");
        Intent intent = new Intent(getContext(), TimeCounterService.class);
        getContext().bindService(intent, this, Context.BIND_AUTO_CREATE);

        getView().postDelayed(() -> {
            if (mTimeCounterService != null) mTimeCounterService.getTimeCounterViewModel().stop();
        }, 10000);
    }

    // ******************************* Implements ServiceConnection ******************************
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i(TAG, "onServiceConnected");
        TimeCounterService.TimeCounterBinder binder = (TimeCounterService.TimeCounterBinder) iBinder;
        mTimeCounterService = binder.getService();
        mTimeCounterService.getTimeCounterViewModel().start();
        mBinding.setTimeCounter(mTimeCounterService.getTimeCounterViewModel());
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i(TAG, "onServiceDisconnected");
        mTimeCounterService = null;
    }
    // ******************************* Implements ServiceConnection ******************************
}
