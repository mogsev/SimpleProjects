package com.mogsev.fragmentapp.gui.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.fragmentapp.R;
import com.mogsev.fragmentapp.databinding.FragmentFirstBinding;
import com.mogsev.fragmentapp.gui.activities.MenuActivity;
import com.mogsev.fragmentapp.gui.activities.SecondActivity;
import com.mogsev.fragmentapp.gui.activities.ThirdActivity;

import timber.log.Timber;

public class FirstFragment extends Fragment implements View.OnClickListener {

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
        Timber.i("onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.i("onViewCreated");
        mBinding.buttonOpenSecondFragment.setOnClickListener(this);
        mBinding.buttonOpenFourthFragment.setOnClickListener(this);
        mBinding.buttonOpenSecondActivity.setOnClickListener(this);
        mBinding.buttonOpenThirdActivity.setOnClickListener(this);
        mBinding.buttonOpenMenuActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button_open_second_fragment:
                fragment = SecondFragment.newInstance();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_right_enter, R.anim.slide_right_exit, R.anim.slide_right_pop_enter, R.anim.slide_right_pop_exit)
                        .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.button_open_fourth_fragment:
                fragment = FourthFragment.newInstance();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_left_enter, R.anim.slide_left_exit, R.anim.slide_left_pop_enter, R.anim.slide_left_pop_exit)
                        .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.button_open_second_activity:
                intent = new Intent(getContext(), SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.button_open_third_activity:
                intent = new Intent(getContext(), ThirdActivity.class);
                startActivity(intent);
                break;
            case R.id.button_open_menu_activity:
                intent = new Intent(getContext(), MenuActivity.class);
                startActivity(intent);
                break;
            default:
                Timber.i("default onClick");
                break;
        }
    }
}
