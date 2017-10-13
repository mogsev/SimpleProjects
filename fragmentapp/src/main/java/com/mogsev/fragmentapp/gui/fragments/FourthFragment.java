package com.mogsev.fragmentapp.gui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.fragmentapp.R;

import timber.log.Timber;

public class FourthFragment extends Fragment {

    public FourthFragment() {
        // Required empty public constructor
    }

    public static FourthFragment newInstance() {
        FourthFragment fragment = new FourthFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Timber.i("onCreateView");
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

}
