package com.mogsev.fragmentapp.gui.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.fragmentapp.R;
import com.mogsev.fragmentapp.databinding.FragmentSecondBinding;

import timber.log.Timber;

public class SecondFragment extends Fragment {

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
        Timber.i("onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.buttonOpenThirdFragment.setOnClickListener((v) -> {
            Fragment fragment = ThirdFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_right_enter, R.anim.slide_right_exit, R.anim.slide_right_pop_enter, R.anim.slide_right_pop_exit)
                    .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });
    }
}
