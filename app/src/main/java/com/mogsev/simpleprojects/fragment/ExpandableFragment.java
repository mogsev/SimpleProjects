package com.mogsev.simpleprojects.fragment;


import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.simpleprojects.R;
import com.mogsev.simpleprojects.databinding.FragmentExpandableBinding;

public class ExpandableFragment extends Fragment {
    private static final String TAG = ExpandableFragment.class.getSimpleName();

    public final ObservableBoolean mFirstRvVisible = new ObservableBoolean(false);
    public final ObservableBoolean mSecondRvVisible = new ObservableBoolean(false);
    public final ObservableBoolean mThirdRvVisible = new ObservableBoolean(false);
    public final ObservableBoolean mFourthRvVisible = new ObservableBoolean(false);
    public final ObservableBoolean mFifthRvVisible = new ObservableBoolean(false);

    private FragmentExpandableBinding mBinding;

    public ExpandableFragment() {
        // Required empty public constructor
    }

    public static ExpandableFragment newInstance() {
        ExpandableFragment fragment = new ExpandableFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_expandable, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");
        mBinding.setFragment(this);
    }
}
