package com.mogsev.simpleprojects.fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
    private boolean mTimeCounterServiceBound = false;

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
        getView().postDelayed(() -> {
            if (mTimeCounterService != null) mTimeCounterService.getTimeCounterViewModel().stop();
        }, 20000);

        getView().postDelayed(() -> {
            if (mTimeCounterService != null) mTimeCounterService.getTimeCounterViewModel().start();
        }, 30000);

        getView().postDelayed(() -> {
            if (mTimeCounterService != null) mTimeCounterService.getTimeCounterViewModel().stop();
        }, 60000);
        mBinding.setFragment(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        // bind to service
        Intent intent = new Intent(getContext(), TimeCounterService.class);
        getContext().bindService(intent, this, 0);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        // Unbind from the service
        if (mTimeCounterServiceBound) {
            getContext().unbindService(this);
            mTimeCounterServiceBound = false;
        }
    }

    // ******************************* Implements ServiceConnection ******************************
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i(TAG, "onServiceConnected");
        TimeCounterService.TimeCounterBinder binder = (TimeCounterService.TimeCounterBinder) iBinder;
        mTimeCounterService = binder.getService();
        mTimeCounterServiceBound = true;
        mBinding.setTimeCounter(mTimeCounterService.getTimeCounterViewModel());
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i(TAG, "onServiceDisconnected");
        mTimeCounterServiceBound = false;
    }
    // ******************************* Implements ServiceConnection ******************************

    public boolean openSecondFragment() {
        SecondFragment fragment = SecondFragment.newInstance();
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
        return true;
    }

    public boolean openExpandableFragment() {
        ExpandableFragment fragment = ExpandableFragment.newInstance();
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
        return true;
    }
}
