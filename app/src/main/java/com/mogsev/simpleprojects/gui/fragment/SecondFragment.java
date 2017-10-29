package com.mogsev.simpleprojects.gui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.simpleprojects.R;
import com.mogsev.simpleprojects.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    private static final String TAG = SecondFragment.class.getSimpleName();

    private FragmentSecondBinding mBinding;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance() {
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false);
        View view = mBinding.getRoot();
        return view;
    }

}
